import {Component, OnInit, ViewChild} from '@angular/core';
import {ServiceProviderCredentials, ServiceProviderType} from "../../_models/client";
import {MessageService} from "../../_services/message.service";
import {AuthenticationService} from "../../_services/authentication.service";
import {SettingsService} from "../../_services/settings.service";

@Component({
  selector: 'app-profile-serviceproviders',
  templateUrl: './serviceproviders.component.html',
  styleUrls: ['./serviceproviders.component.css']
})
export class ServiceprovidersComponent implements OnInit {

  @ViewChild("f") form: any;
  serviceProviderCredentials: ServiceProviderCredentials = new ServiceProviderCredentials();

  serviceProviderTypes: string[];
  serviceProviders: string[];
  serviceProviderFields: any = {};

  constructor(private messageService: MessageService, private authenticationService: AuthenticationService,
              public settingsService: SettingsService) {
    this.initServiceProviderTypes();
    this.initServiceProviders();
  }

  ngOnInit() {
    this.authenticationService.getServiceProviderCredentialsEmail().subscribe(
      response => {
        console.log(response);
        // this.serviceProviderCredentials = response[0];
      }
    );
  }

  initServiceProviderTypes() {
    this.setServiceProviderTypes();
  }

  initServiceProviders() {
    this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
  }

  setServiceProviderTypes() {
    let spTypes = Object.keys(this.settingsService.serviceProviders);
    if (!this.serviceProviderCredentials.serviceProviderType)
      this.serviceProviderCredentials.serviceProviderType = spTypes[0];
    console.log(spTypes);
    this.serviceProviderTypes = spTypes;
  }

  setServiceProviders(serviceProviderType: string) {
    let sp = Object.keys(this.settingsService.serviceProviders[serviceProviderType]);
    this.serviceProviderCredentials.serviceProvider = sp[0];
    this.setServiceProviderFields();
    this.serviceProviders = sp;
    this.serviceProviderCredentials.credentialsMap = {};
  }

  onChangeServiceProviderType() {
    console.log(this.serviceProviderCredentials.serviceProviderType);
    let sp = this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
  }

  onChangeServiceProvider() {
    this.setServiceProviderFields();
  }

  setServiceProviderFields() {
    let f = this.settingsService.serviceProviders[this.serviceProviderCredentials.serviceProviderType][this.serviceProviderCredentials.serviceProvider]["fields"];
    console.log(f);
    this.serviceProviderFields = f;
  }

  onSave(form: FormData) {
    if (this.form.valid) {
      this.authenticationService.saveServiceProviderCredentialEmail(this.serviceProviderCredentials)
        .subscribe(
          response => {
          }
        );
      console.log(JSON.stringify(this.serviceProviderCredentials));
    }
  }

}
