import {Component, Input, OnInit} from '@angular/core';
import {CampaignTime} from "../../../_models/campaign";

@Component({
  selector: 'app-date-time',
  templateUrl: './date-time.component.html',
  styleUrls: ['./date-time.component.css']
})
export class DateTimeComponent implements OnInit {
  showCloseButton:boolean;
  _ref:any;
  campaignTime=new CampaignTime();
  date=new Date();
  @Input() campaignTimes;
  //Date Picker
  public singleDate: any;
  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };

  constructor() {
    this.singleDate = Date.now();
  }
  ngOnInit() {
    this.campaignTime.hours= this.date.getHours() > 12 ? this.date.getHours() - 12 : this.date.getHours();
    this.campaignTime.minutes=this.date.getMinutes();
    this.campaignTime.date=this.singleDate;
    this.campaignTimes.push(this.campaignTime);
  }
  singleSelect(value: any) {
    this.singleDate = value.start;
  }
  removeObject(){
    this.removeCampaignTime();
    this._ref.destroy();
  }
  removeCampaignTime() {
    this.campaignTimes.forEach((data, index)=>{
      if(data == this.campaignTime)
        this.campaignTimes.splice(index,1);
    })
  }

}
