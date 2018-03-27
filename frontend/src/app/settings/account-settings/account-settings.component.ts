import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})
export class AccountSettingsComponent implements OnInit {
  // https://samuelnygaard.github.io/ng2-timezone-selector/docs/
  user = {
    timezone:"Asia/Kolkata"
  };
  placeholderString = 'Select timezone';
  showCodeBlock=1;
  changeTimezone(timezone) {
    console.log(timezone);
    // this.user.timezone = timezone;
    // console.log(this.user.timezone);
  }
  constructor() { }

  ngOnInit() {
  }

}
