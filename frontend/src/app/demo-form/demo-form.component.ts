import {Component, Input, OnInit} from '@angular/core';
import {RegistrationRequest} from "../_models/client";
import {AuthenticationService} from "../_services/authentication.service";

@Component({
  selector: 'app-demo-form',
  templateUrl: './demo-form.component.html',
  styleUrls: ['./demo-form.component.scss']
})
export class DemoFormComponent implements OnInit {
  visitorName: string;
  preferredCountries = ['in', 'us', 'ru', 'gb'];
  @Input() modalDemoButton;
  demoRequest: RegistrationRequest = new RegistrationRequest();

  constructor(private authenticationService : AuthenticationService) {
  }

  ngOnInit() {
  }
  submitDemoForm() {
    this.demoRequest.firstName = this.visitorName.substring(0,this.visitorName.indexOf(" "));
    this.demoRequest.lastName = this.visitorName.substring(this.visitorName.indexOf(" ")+1);
    this.demoRequest.password='aAbBcC123@!';
    this.authenticationService.register(this.demoRequest)
      .subscribe(
        (response) => {
          console.log(response);
        }
      )
  }
}
