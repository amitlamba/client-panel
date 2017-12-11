import {Component} from '@angular/core';
import {AuthenticationService} from "../_services/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',

})

export class HeaderComponent {

  constructor(private authenticationService: AuthenticationService) {

  }

  clientName(): string {
    return this.authenticationService.getUsername();
  }
}
