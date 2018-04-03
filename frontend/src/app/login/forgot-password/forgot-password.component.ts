import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthenticationService} from "../../_services/authentication.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  @ViewChild('f') signupForm:NgForm;

  user:any = {
    email:''
  };
  constructor(private authenticationService: AuthenticationService,
              private router:Router) { }

  ngOnInit() {
  }
  onCancel() {
    this.signupForm.reset();
  }
  onSubmit() {
    this.authenticationService.forgotpassword(this.signupForm.value.email+'/')
      .subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/']);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    this.signupForm.reset();
  }

}
