import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AccountSettings} from "../../_models/client";

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})
export class AccountSettingsComponent implements OnInit {
  jsCodeSnippet: string = "";
  showCodeBlock: boolean = true;
  accountSettings: AccountSettings = new AccountSettings();
  protocol: string = 'https://';
  websiteURL: string;
  // invalidWebsiteUrl
  // websiteUrlRegex:any=/^((https?|ftp|smtp):\/\/)?(www.)?[a-z0-9]+(\.[a-z]{2,}){1,3}(#?\/?[a-zA-Z0-9#]+)*\/?(\?[a-zA-Z0-9-_]+=[a-zA-Z0-9-%]+&?)?$/;

  // ng2-timezone-picker is used from https://samuelnygaard.github.io/ng2-timezone-selector/docs/
  placeholderString = 'Select timezone';

  changeTimezone(timezone) {
    this.accountSettings.timezone = timezone;
  }

  constructor() {
  }

  ngOnInit() {
    this.accountSettings.timezone = "Asia/Kolkata";
    this.accountSettings.url=[];
    let a=["www.facebook.com","www.sample.gov.bd","https://www.sample.com#","http://www.sample.com/xyz","http://www.sample.com/#xyz","www.sample.com","www.sample.com/xyz/#/xyz","sample.com","sample.com?name=foo","http://www.sample.com#xyz","http://www.sample.c"];
    let re=/^((https?|ftp|smtp):\/\/)?(www.)?[a-z0-9]+(\.[a-z]{2,}){1,3}(#?\/?[a-zA-Z0-9#]+)*\/?(\?[a-zA-Z0-9-_]+=[a-zA-Z0-9-%]+&?)?$/;
    a.map(x=>console.log(x+" => "+re.test(x)));
  }

  changeTooltipText() {
    // (<any>$('#copyCode')).on('show.bs.tooltip', function(){
    //   // console.log("hello");
    //   (<any>$('#copyCode')).tooltip('show').attr('data-original-title','Copied') ;
    // });
  }

  getJSIntegrationCode() {
    this.showCodeBlock = !this.showCodeBlock;
    this.accountSettings.url.push(this.protocol + this.websiteURL);
    console.log(this.accountSettings);
  }
}
