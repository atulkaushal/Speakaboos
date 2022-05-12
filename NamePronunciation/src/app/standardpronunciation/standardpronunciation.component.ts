import { Component, OnInit } from '@angular/core';
import {FormControl,FormGroup,Validators} from '@angular/forms'

@Component({
  selector: 'app-standardpronunciation',
  templateUrl: './standardpronunciation.component.html',
  styleUrls: ['./standardpronunciation.component.css']
})
export class StandardpronunciationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
employeeForm: FormGroup = new FormGroup(
{
'employeeIdInput': new FormControl ('',[Validators.required])
})
searchUser():void{}
}
