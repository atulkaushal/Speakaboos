import { Component, OnInit } from '@angular/core';
import {FormControl,FormGroup,Validators,ReactiveFormsModule } from '@angular/forms'
import {EmployeeDetailRequest} from "../model/employee-details-request.model";
import {EmployeeDetailResponse} from "../model/employee-details-response.model";
import {EmployeePreferenceDetailRequest} from "../model/employee-preferencedetails-request.model";
import {EmployeePronunciationDetailRequest} from "../model/employee-pronunciationdetails-request.model";
import {StatusMessageResponse} from "../model/employee-preferencedetails-response.model";
import {Constants} from "../const/app-constants";
import {EmployeeService} from "../service/employee.service";
@Component({
  selector: 'app-standardpronunciation',
  templateUrl: './standardpronunciation.component.html',
  styleUrls: ['./standardpronunciation.component.css']
})
export class StandardpronunciationComponent implements OnInit {
locale : any [] =Constants.localeData;
integerPattern : string = '\\d+$';

employeeId : number = 0;
request :EmployeeDetailRequest =new EmployeeDetailRequest();
employeeResponse :EmployeeDetailResponse =new EmployeeDetailResponse();

preferenceRequest : EmployeePreferenceDetailRequest = new EmployeePreferenceDetailRequest();

pronunciationRequest : EmployeePronunciationDetailRequest = new EmployeePronunciationDetailRequest();
//preferenceResponse : EmployeePreferenceDetailResponse = new EmployeePreferenceDetailResponse();




  constructor( private employeeService:EmployeeService) {

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

  populateDefaultValues():void{
  console.log("populateDefaultValues");
  this.preferenceRequest.optOutFlag= false;
  this.preferenceRequest.locale='en-US';
  this.preferenceRequest.speed= 2;
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
console.log("Search success");
this.employeeResponse=response;
this.employeeResponse.employeeId= response.employeeId;
this.employeeResponse.legalFirstName= response.legalFirstName;
this.employeeResponse.legalLastName= response.legalLastName;
this.employeeResponse.preferredName= response.preferredName;

if(response.employeeId!=0 && response.preferredName!='undefined' && response.preferredName)
{
this.populateDefaultValues();
this.getPreference();
}


});


}

getPreference():void{

//populate request populateDefaultValues
this.pronunciationRequest.employeeId = this.employeeResponse.employeeId;
this.pronunciationRequest.name = this.employeeResponse.preferredName;
this.pronunciationRequest.speed = 1;
this.pronunciationRequest.language='en-US';
this.employeeService.getEmployeePronunciation(this.pronunciationRequest).subscribe ((response:any)=>{
//TODO map response

console.log("getPreference success");
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
console.log("savePreference"+this.preferenceRequest.employeeId);
console.log("savePreference"+this.preferenceRequest.optOutFlag);
console.log("savePreference"+this.preferenceRequest.locale);
console.log("savePreference"+this.preferenceRequest.speed);

this.employeeService.saveEmployeePreference(this.preferenceRequest).subscribe ((response:StatusMessageResponse)=>{

console.log("Save success");
});
}

}
