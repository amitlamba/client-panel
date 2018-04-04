import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AmPm, CampaignTime} from "../../../_models/campaign";
import * as moment from "moment";

@Component({
  selector: 'app-date-time',
  templateUrl: './date-time.component.html',
  styleUrls: ['./date-time.component.scss']
})
export class DateTimeComponent implements OnInit {
  showCloseButton: boolean;
  _ref: any;
  campaignTime: CampaignTime = new CampaignTime();
  date = new Date();
  campaignTimeAmpPmList: string[];

  @Input() campaignTimesList:CampaignTime[] = [];
  localCampaignLaterTime;
  @Input() get campaignLaterTime(): CampaignTime {
    return this.localCampaignLaterTime;
  }
  set campaignLaterTime(campaignLaterTime: CampaignTime) {
    this.localCampaignLaterTime = campaignLaterTime;
    this.campaignLaterTimeChange.emit(this.localCampaignLaterTime);
  }
  @Output() campaignLaterTimeChange = new EventEmitter();

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
    this.campaignTimeAmpPmList = Object.keys(AmPm);
  }

  ngOnInit() {
    this.campaignTime.hours = this.date.getHours() > 12 ? this.date.getHours() - 12 : this.date.getHours();
    this.campaignTime.minutes = this.date.getMinutes();
    this.campaignTime.ampm = this.date.getHours() < 12 ? AmPm.AM : AmPm.PM;
    this.campaignTime.date = moment(Date.now()).format("YYYY-MM-DD");
    this.campaignTimesList.push(this.campaignTime);
    this.campaignLaterTime=this.campaignTime;
  }

  singleSelect(val: any): void {
    this.campaignTime.date = moment(val.end.valueOf()).format("YYYY-MM-DD");
  }

  removeObject(): void {
    this.removeCampaignTime();
    this._ref.destroy();
  }

  removeCampaignTime(): void {
    this.campaignTimesList.forEach((data, index) => {
      if (this.campaignTime == data) {
        this.campaignTimesList.splice(index, 1);
      }
    })
  }

}
