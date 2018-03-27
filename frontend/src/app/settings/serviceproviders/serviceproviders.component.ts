import {Component, OnInit, ViewChild} from '@angular/core';
import {ServiceProviderCredentials, ServiceProviderType} from "../../_models/client";
import {MessageService} from "../../_services/message.service";
import {AuthenticationService} from "../../_services/authentication.service";
import {SettingsService} from "../../_services/settings.service";
import {Router} from "@angular/router";
import {parseHttpResponse} from "selenium-webdriver/http";

@Component({
  selector: 'app-profile-serviceproviders',
  templateUrl: './serviceproviders.component.html',
  styleUrls: ['./serviceproviders.component.css']
})
export class ServiceprovidersComponent implements OnInit {

  @ViewChild("f") form: any;
  serviceProviderCredentials: ServiceProviderCredentials = new ServiceProviderCredentials();
  serviceProviderTypes: string[];
  serviceProviders: string[] = [];
  serviceProviderFields: any = {};

  constructor(private messageService: MessageService,
              private authenticationService: AuthenticationService,
              public settingsService: SettingsService,
              private router:Router) {
    this.initServiceProviderTypes();
    // this.initServiceProviders();
  }

  ngOnInit() {
    this.settingsService.getServiceProvidersList().subscribe(
      response => {
        console.log(response);
      }
    );
  }

  initServiceProviderTypes() {
    this.setServiceProviderTypes();
  }

  initServiceProviders() {
    // this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
  }

  setServiceProviderTypes() {
    let spTypes = Object.keys(this.settingsService.serviceProviders);
    // if (!this.serviceProviderCredentials.serviceProviderType)
    //   this.serviceProviderCredentials.serviceProviderType = spTypes[0];
    // console.log(spTypes);
    this.serviceProviderTypes = spTypes;
  }

  setServiceProviders(serviceProviderType: string) {
    let sp = Object.keys(this.settingsService.serviceProviders[serviceProviderType]['providers']);
    this.serviceProviderCredentials.serviceProvider = sp[0];
    this.setServiceProviderFields();
    this.serviceProviders = sp;
    this.serviceProviderCredentials.credentialsMap = {};
  }

  onChangeServiceProviderType(data: string) {
    this.serviceProviderCredentials.serviceProviderType = data;
    console.log(this.serviceProviderCredentials.serviceProviderType);
    this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
  }

  onChangeServiceProvider() {
    this.setServiceProviderFields();
  }

  setServiceProviderFields() {
    let f = this.settingsService.serviceProviders[this.serviceProviderCredentials.serviceProviderType]['providers'][this.serviceProviderCredentials.serviceProvider]["fields"];
    this.serviceProviderFields = f;
    this.serviceProviderCredentials.credentialsMap = {};
  }

  onSave(form: FormData) {
    console.log(JSON.stringify(this.serviceProviderCredentials));
    if (this.form.valid) {
      this.settingsService.saveServiceProviderCredentialEmail(this.serviceProviderCredentials).subscribe();
      this.settingsService.getServiceProvidersList().subscribe(
        response =>{
          console.log(response);
        }
      )
      // if (this.serviceProviderCredentials.serviceProviderType === 'Email Service Provider')
      //   this.settingsService.saveServiceProviderCredentialEmail(this.serviceProviderCredentials).subscribe();
      // if (this.serviceProviderCredentials.serviceProviderType === 'SMS Service Provider')
      //   this.settingsService.saveServiceProviderCredentialsSms(this.serviceProviderCredentials).subscribe();
    }
    this.router.navigate(["settings/service-provider-settings"]);
  }
}
