import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ServiceProvider, ServiceProviderCredentials, ServiceProviderType} from "../../_models/client";
import {MessageService} from "../../_services/message.service";
import {AuthenticationService} from "../../_services/authentication.service";

@Component({
  selector: 'app-profile-serviceproviders',
  templateUrl: './serviceproviders.component.html',
  styleUrls: ['./serviceproviders.component.css']
})
export class ServiceprovidersComponent implements OnInit {

  @ViewChild("f") form: any;
  serviceProviderTypes: string[] = [];
  serviceProviders: string[] = [];
  serviceProviderCredentials: ServiceProviderCredentials = new ServiceProviderCredentials();

  constructor(private messageService: MessageService, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.initServiceProviderTypes();
    this.initServiceProviders();
    this.authenticationService.getServiceProviderCredentialsEmail().subscribe(
      response => {
        console.log(response);
        this.serviceProviderCredentials = response[0];
      }
    );
  }

  initServiceProviderTypes() {
    const objValues = Object.keys(ServiceProviderType).map(k => ServiceProviderType[k]);
    this.serviceProviderTypes = objValues.filter(v => typeof v === "string") as string[];
  }

  initServiceProviders() {
    const objValues = Object.keys(ServiceProvider).map(k => ServiceProvider[k]);
    this.serviceProviders = objValues.filter(v => typeof v === "string") as string[];
  }

  onSave(form: FormData) {
    if (this.form.valid) {
      this.authenticationService.saveServiceProviderCredentialEmail(this.serviceProviderCredentials)
        .subscribe(
          response => {
          }
        );
    }
  }

}
