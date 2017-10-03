import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Http, Response, RequestOptions, Headers} from '@angular/http';
import { HttpClient } from '@angular/common/http';

import { NgModule, ViewChild } from '@angular/core';
import { FormsModule,FormGroup,FormControl } from '@angular/forms';

class Signup {
  constructor(public firstName: string = '',
              public lastName: string = '',
              public email: string = '',
              public company: string = '',
              public password: string = '',
              public mobile :number=190,
              public comments: string='',
              public country : string='',
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

  onSubmit(form: FormData) {
    if (this.form.valid) {
      const body = {
        email: this.model.email,
        company:this.model.company,
        firstname: this.model.firstName,
        lastname:this.model.lastName,
        mobile: this.model.mobile,
        country:this.model.country,
        comments:this.model.comments
      };
      this.http
      .post('http://localhost:8080/register', body)
      .map((response: Response) =>{
          console.log(response.type);
          let json = JSON.stringify(response);
          let finalJson = JSON.parse(json);
          return finalJson;
          })
      .subscribe(
          (response: JSON) => {

                          }
                );

    }
  }


  constructor(private http: HttpClient) {

  }
  ngOnInit(){
  
 }

}
