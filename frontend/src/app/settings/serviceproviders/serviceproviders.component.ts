import { Component, OnInit } from '@angular/core';
import {ServiceProvider, ServiceProviderType} from "../../_models/client";

@Component({
  selector: 'app-profile-serviceproviders',
  templateUrl: './serviceproviders.component.html',
  styleUrls: ['./serviceproviders.component.css']
})
export class ServiceprovidersComponent implements OnInit {

  serviceProviderTypes: string[] = [];
  serviceProviders: string[] = [];

  constructor() { }

  ngOnInit() {
    this.initServiceProviderTypes();
    this.initServiceProviders();
    console.log(this.serviceProviders);
  }

  initServiceProviderTypes() {
    const objValues = Object.keys(ServiceProviderType).map(k => ServiceProviderType[k]);
    this.serviceProviderTypes = objValues.filter(v => typeof v === "string") as string[];
  }

  initServiceProviders() {
    const objValues = Object.keys(ServiceProvider).map(k => ServiceProvider[k]);
    this.serviceProviders = objValues.filter(v => typeof v === "string") as string[];
  }
}
