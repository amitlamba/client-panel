import {Component, Input, OnInit} from '@angular/core';
import {AmPm, CampaignTime} from "../../../_models/campaign";
import * as moment from "moment";

@Component({
  selector: 'app-date-time',
  templateUrl: './date-time.component.html',
  styleUrls: ['./date-time.component.css']
})
export class DateTimeComponent implements OnInit {
  showCloseButton: boolean;
  _ref: any;
  campaignTime: CampaignTime = new CampaignTime();
  date = new Date();
  campaignTimeAmpPmList:string[];
  @Input() campaignTimes;

  // Date Picker Options
  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right",
    locale: {
      format: "DD/MM/YYYY"
    }
  };

  constructor() {
    this.campaignTimeAmpPmList =Object.keys(AmPm);
  }

  ngOnInit() {
    this.campaignTime.hours = this.date.getHours() > 12 ? this.date.getHours() - 12 : this.date.getHours();
    this.campaignTime.minutes = this.date.getMinutes();
    this.campaignTime.ampm=this.date.getHours()<12 ? AmPm.AM : AmPm.PM;
    console.log(this.campaignTime.ampm);
    this.campaignTime.date = moment(Date.now()).format("YYYY-MM-DD");
    this.campaignTimes.push(this.campaignTime);
  }

  singleSelect(val: any): void {
    this.campaignTime.date = moment(val.end.valueOf()).format("YYYY-MM-DD");
  }

  removeObject(): void {
    this.removeCampaignTime();
    this._ref.destroy();
  }

  removeCampaignTime(): void {
    this.campaignTimes.forEach((data, index) => {
      if (data == this.campaignTime)
        this.campaignTimes.splice(index, 1);
    })
  }

}
