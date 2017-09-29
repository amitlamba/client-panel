import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Http, Response, RequestOptions, Headers} from '@angular/http';

import { NgModule, ViewChild } from '@angular/core';
import { FormsModule,FormGroup,FormControl } from '@angular/forms';

class Signup {
  constructor(public firstName: string = '',
              public lastName: string = '',
              public email: string = '',
              public password: string = '',
              public language: string = '') {
  }
}
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model: Signup = new Signup();
  @ViewChild('f') form: any;

  langs: string[] = [
    'English',
    'French',
    'German',
  ];

  onSubmit() {
    if (this.form.valid) {
      console.log("Form Submitted!");
      this.form.reset();
    }
  }


  constructor(private http: Http) {

  }
  ngOnInit(){
    this.http.get('https://userndot-a528b.firebaseio.com/code.json')
    .subscribe(
      (res:Response)=> {
                const data = res.json();
                console.log(data);
                    }
            );
 }

}
