import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders,HttpResponse} from '@angular/common/http';
import {EmployeeDetailRequest} from "../model/employee-details-request.model";
import {EmployeeDetailResponse} from "../model/employee-details-response.model";
import {EmployeePreferenceDetailRequest} from "../model/employee-preferencedetails-request.model";
import {EmployeePronunciationDetailRequest} from "../model/employee-pronunciationdetails-request.model";
import {StatusMessageResponse} from "../model/employee-preferencedetails-response.model";
import {StatusMessage} from "../model/status-message.model";

import {Observable} from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
baseURL : string ='http://localhost:8080/pronunciation/V1'

  constructor(private httpClient:HttpClient) { }


  getEmployeeDetails(request: EmployeeDetailRequest) : Observable <EmployeeDetailResponse>
  {

    return this.httpClient.post<any>(this.baseURL+'/getEmployeeDetails/V1',request);
  }


   saveEmployeePreference(request: EmployeePreferenceDetailRequest) : Observable <StatusMessageResponse>
    {

      return this.httpClient.post<any>(this.baseURL+'/savePronunciationInformation/V1',request);

    }


getAudio(request: EmployeePronunciationDetailRequest) : Observable <any>
        {

	        	let headers = new HttpHeaders({
	        'Accept':'application/octet-stream' });

		         return this.httpClient.post<any>(this.baseURL+'/getPronunciationInformation/V1',request,{headers: headers,responseType: 'blob' as 'json'});

        }

        saveAudio(file: File, employeeId : number) : Observable <any>
                {
//const file:File = "C:\\Users\\13142\\Downloads\\sample-3s.mp3";

const formData = new FormData();
formData.append('file',file);
        	        	let headers = new HttpHeaders({

        	         'EmployeeId':'4'});

        		         return this.httpClient.post<any>(this.baseURL+'/savePronunciationInformation/V1',formData,{headers: headers});

                }


    getEmployeePronunciation(request: EmployeePronunciationDetailRequest) : Observable <any>
        {



		         return this.httpClient.post<any>(this.baseURL+'/getPronunciationInformation/V1',request);

        }
}