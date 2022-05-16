//import {Recorder} from 'recorder-js';
import { Component, OnInit } from '@angular/core';
import {FormControl,FormGroup,Validators,ReactiveFormsModule } from '@angular/forms'
import {EmployeeDetailRequest} from "../model/employee-details-request.model";
import {EmployeeDetailResponse} from "../model/employee-details-response.model";
import {EmployeePreferenceDetailRequest} from "../model/employee-preferencedetails-request.model";
import {EmployeePronunciationDetailRequest} from "../model/employee-pronunciationdetails-request.model";
import {EmployeePronunciationDetailResponse} from "../model/employee-pronunciationdetails-response.model";
import {StatusMessage} from "../model/status-message.model";
import {StatusMessageResponse} from "../model/employee-preferencedetails-response.model";
import {Constants} from "../const/app-constants";
import {EmployeeService} from "../service/employee.service";
import {HttpClient,HttpResponse} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';



declare var $: any;
import * as RecordRTC from 'recordrtc';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-standardpronunciation',
  templateUrl: './standardpronunciation.component.html',
  styleUrls: ['./standardpronunciation.component.css']
})


export class StandardpronunciationComponent implements OnInit {
locale : any [] =Constants.localeData;
integerPattern : string = '\\d+$';

//Lets declare Record OBJ
record :any;
//Will use this flag for toggeling recording
recording = false;
//URL of Blob
url: string = '';
error= '';

employeeId : number = 0;
request :EmployeeDetailRequest =new EmployeeDetailRequest();
employeeResponse :EmployeeDetailResponse =new EmployeeDetailResponse();

preferenceRequest : EmployeePreferenceDetailRequest = new EmployeePreferenceDetailRequest();

pronunciationRequest : EmployeePronunciationDetailRequest = new EmployeePronunciationDetailRequest();
pronunciationResponse : EmployeePronunciationDetailResponse = new EmployeePronunciationDetailResponse();





  constructor( private employeeService:EmployeeService,private toastr : ToastrService,private domSanitizer: DomSanitizer) {

  }
optOutFlag=false;

  ngOnInit(): void {
  this.onChanges();

  }

  onChanges():void
  {
  this.employeeForm.controls['employeeIdInput'].valueChanges.subscribe(value=>{
  this.employeeId = value;
  }

  )

  }


employeeForm: FormGroup = new FormGroup(
{
'employeeIdInput': new FormControl ('',[Validators.required,Validators.pattern(this.integerPattern)]),
'optOutFlag': new FormControl ('',null),
'locale': new FormControl ('',null),
'speed': new FormControl ('',null)
})


searchUser():void{


this.request.employeeId = this.employeeForm.controls['employeeIdInput'].value;



this.employeeService.getEmployeeDetails(this.request).subscribe ((response:EmployeeDetailResponse)=>{

this.employeeResponse=response;
this.employeeResponse.employeeId= response.employeeId;
this.employeeResponse.legalFirstName= response.legalFirstName;
this.employeeResponse.legalLastName= response.legalLastName;
this.employeeResponse.preferredName= response.preferredName;

if(response.employeeId!=0 && response.preferredName!='undefined' && response.preferredName)
{
this.getPreference();
}


});


}

getPreference():void{

//populate request populateDefaultValues
this.pronunciationRequest.employeeId = this.employeeResponse.employeeId;
this.pronunciationRequest.name = this.employeeResponse.preferredName;


this.employeeService.getEmployeePronunciation(this.pronunciationRequest).subscribe ((response:EmployeePronunciationDetailResponse)=>{

this.pronunciationResponse.speed = response.speed;
this.pronunciationResponse.language = response.language;



this.employeeForm.controls['locale'].setValue(this.locale.filter(item => item.locale == response.language)[0]);
this.employeeForm.controls['speed'].setValue(response.speed.toString());



});
}

savePreference():void{



const employeeIdInput = this.employeeForm.controls['employeeIdInput'].value;
if(employeeIdInput!=0)
{
this.preferenceRequest.employeeId = employeeIdInput;
}
const optOutFlag = this.employeeForm.controls['optOutFlag'].value;
if(optOutFlag!='undefined' && optOutFlag)
{

this.preferenceRequest.optOutFlag = this.employeeForm.controls['optOutFlag'].value;
}

const locale= this.employeeForm.controls['locale'].value?.locale;
if(locale!='undefined' && locale)

{
this.preferenceRequest.locale = locale ;
}

const speed = this.employeeForm.controls['speed'].value;
if(speed!='undefined' && speed)
{
this.preferenceRequest.speed =speed ;
}

this.employeeService.saveEmployeePreference(this.preferenceRequest).subscribe ((response:StatusMessageResponse)=>{



{
this.toastr.success('Record Successfully Saved','Success Message',
{
disableTimeOut:false,
timeOut : 10000,
tapToDismiss : true,
toastClass : "toast-success",
closeButton : true,
positionClass : 'toast-top-full-width'
});

}
});

this.employeeForm.controls['employeeIdInput'].setValue(0);
this.employeeForm.controls['locale'].setValue('');
this.employeeForm.controls['speed'].setValue("1");


}

playPronunciation():void{
this.pronunciationRequest.employeeId = this.employeeForm.controls['employeeIdInput'].value;
this.employeeService.getAudio(this.pronunciationRequest).subscribe ((response:any)=>{

  let audio=new Audio(URL.createObjectURL(new Blob([response], {type: "audio/mp3"})));
  audio.load();
  audio.play();
});
}

onFilechange(event: any) {
   console.log(event.target.files[0]);
  let file :File = event.target.files[0];
   if (file) {
       this.employeeService.saveAudio(file,this.employeeForm.controls['employeeIdInput'].value).subscribe ((response:any)=>{
          alert("Uploaded")
        })
      } else {
        alert("Please select a file first")
      }

}


 sanitize(url: string) {
    return this.domSanitizer.bypassSecurityTrustUrl(url);
  }

/**
* Start recording.
*/
initiateRecording() {
this.recording = true;
let mediaConstraints = {
video: false,
audio: true
};
this.url='';
navigator.mediaDevices.getUserMedia(mediaConstraints).then(this.successCallback.bind(this), this.errorCallback.bind(this));
}

/**
* Will be called automatically.
*/
successCallback(stream: any) {
var options = {
mimeType: "audio/wav",
numberOfAudioChannels: 1,
sampleRate: 48000,
};
//Start Actuall Recording
var StereoAudioRecorder = RecordRTC.StereoAudioRecorder;
this.record = new StereoAudioRecorder(stream, options);
this.record.record();
}

/**
* Process Error.
*/
errorCallback(error:any) {
this.error = 'Can not play audio in your browser';
}


/**
* Stop recording.
*/
stopRecording() {
this.recording = false;
this.record.stop(this.processRecording.bind(this));
}
/**
* processRecording Do what ever you want with blob
* @param  {any} blob Blog
*/
processRecording(blob: Blob) {
this.url = URL.createObjectURL(blob);
console.log("blob", blob);
console.log("url", this.url);
}
}
