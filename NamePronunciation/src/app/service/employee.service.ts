import { Injectable } from '@angular/core';
import {HttpClient,HttpResponse} from '@angular/common/http';
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
  //return new Observable<EmployeeDetailResponse>();
    return this.httpClient.post<any>(this.baseURL+'/getEmployeeDetails/V1',request);
  }


   saveEmployeePreference(request: EmployeePreferenceDetailRequest) : Observable <StatusMessageResponse>
    {
    //return new Observable<any()>();
      return this.httpClient.post<any>(this.baseURL+'/savePronunciationInformation/V1',request);
      // console.log("save called");
    //return new Observable<StatusMessageResponse>();
    }

    getEmployeePronunciation(request: EmployeePronunciationDetailRequest) : Observable <any>
        {
         console.log("getEmployeePronunciation called");
        //return new Observable<any()>();
          return this.httpClient.post<any>(this.baseURL+'/getPronunciationInformation/V1',request);

        //return new Observable<StatusMessageResponse>();
        }
}
