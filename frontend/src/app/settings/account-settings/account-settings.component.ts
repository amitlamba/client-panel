import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AccountSettings} from "../../_models/client";
import {SettingsService} from "../../_services/settings.service";

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
  myArray: string[] = ['http://', 'https://'];

  // ng2-timezone-picker is used from https://samuelnygaard.github.io/ng2-timezone-selector/docs/
  placeholderString = 'Select timezone';

  changeTimezone(timezone) {
    this.accountSettings.timezone = timezone;
  }

  constructor(private settingsService: SettingsService) {
  }

  ngOnInit() {
    this.accountSettings.timezone = "Asia/Kolkata";
    this.accountSettings.urls = [];
    this.settingsService.getAccountSettings()
      .subscribe(
        (accountSettings) => {
          this.accountSettings.id = accountSettings.id;
          if (accountSettings.urls[0].substring(0, accountSettings.urls[0].indexOf("/") + 2 == 'https://')) {
            this.protocol = accountSettings.urls[0].substring(0, accountSettings.urls[0].indexOf("/") + 2);
          }
          else {
            this.protocol = accountSettings.urls[0].substring(0, accountSettings.urls[0].indexOf("/") + 2)
          }

          this.websiteURL = accountSettings.urls[0].substring(accountSettings.urls[0].indexOf("/") + 2);
        }
      );
  }


  getJSIntegrationCode() {
    this.showCodeBlock = !this.showCodeBlock;
    this.accountSettings.urls.push(this.protocol + this.websiteURL);
    // this.accountSettings.id = 1;
    console.log(this.accountSettings);
    this.settingsService.saveAccountSettings(this.accountSettings)
      .subscribe(
        (response) => {
          this.settingsService.refreshToken().subscribe(
            (response) => {
              console.log(response);
            }
          )
        }
      );
  }
}
