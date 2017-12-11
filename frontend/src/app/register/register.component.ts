import {Component, OnInit, ViewChild} from '@angular/core';
import {RegistrationRequest} from "../_models/client";
import {AuthenticationService} from "../_services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  model: RegistrationRequest = new RegistrationRequest();
  @ViewChild('f') form: any;
  loading = false;
  error = '';

  langs: string[] = [
    'English',
    'French',
    'German',
  ];

  onSubmit(form: FormData) {
    this.loading = true;
    if (this.form.valid) {
      console.log(this.form);
      console.log(this.model);
      this.authenticationService.register(this.model)
        .subscribe(
          response => {
            this.router.navigate(['/login']);
          },
          (error: Error) => {
            this.error = "Not Registered. Server Error: " + error.message;
            this.loading = false;
          }
        );
    }

  }


  ngOnInit() {

  }

}
