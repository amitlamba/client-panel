import {Component, OnInit, ViewChild} from '@angular/core';
import {ServiceProviderCredentials, ServiceProviderType} from "../../_models/client";
import {MessageService} from "../../_services/message.service";
import {AuthenticationService} from "../../_services/authentication.service";
import {SettingsService} from "../../_services/settings.service";

@Component({
  selector: 'app-profile-serviceproviders',
  templateUrl: './serviceproviders.component.html',
  styleUrls: ['./serviceproviders.component.scss']
})
export class ServiceprovidersComponent implements OnInit {

  @ViewChild("f") form: any;
  serviceProviderCredentials: ServiceProviderCredentials = new ServiceProviderCredentials();
  serviceProviderTypes: string[];
  serviceProviders: string[]=[];
  serviceProviderFields: any = {};

  constructor(private messageService: MessageService, private authenticationService: AuthenticationService,
              public settingsService: SettingsService) {
    this.initServiceProviderTypes();
    // this.initServiceProviders();
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
    // this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
  }

  setServiceProviderTypes() {
    let spTypes = Object.keys(this.settingsService.serviceProviders);
    // if (!this.serviceProviderCredentials.serviceProviderType)
    //   this.serviceProviderCredentials.serviceProviderType = spTypes[0];
    // console.log(spTypes);
    this.serviceProviderTypes = spTypes;
  }
  setServiceProviderType(data){
    this.serviceProviderCredentials.serviceProviderType=data;
  }
  setServiceProviders(serviceProviderType: string) {
    let sp = Object.keys(this.settingsService.serviceProviders[serviceProviderType]['providers']);
    this.serviceProviderCredentials.serviceProvider = sp[0];
    this.setServiceProviderFields();
    this.serviceProviders = sp;
    this.serviceProviderCredentials.credentialsMap = {};
  }

  onChangeServiceProviderType(data:string) {
    // console.log(data);
    this.setServiceProviderType(data);
    console.log(this.serviceProviderCredentials.serviceProviderType);
    this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
    // let sp = this.setServiceProviders(this.serviceProviderCredentials.serviceProviderType);
  }

  onChangeServiceProvider() {
    this.setServiceProviderFields();
  }

  setServiceProviderFields() {
    let f = this.settingsService.serviceProviders[this.serviceProviderCredentials.serviceProviderType]['providers'][this.serviceProviderCredentials.serviceProvider]["fields"];
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
