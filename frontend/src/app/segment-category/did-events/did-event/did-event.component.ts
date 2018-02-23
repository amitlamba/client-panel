import {Component, OnInit} from '@angular/core';
import {DaterangepickerConfig} from "ng2-daterangepicker";
import {IMyDrpOptions} from 'mydaterangepicker';
import {RegisteredEvent, RegisteredEventProperties} from "../../../_models/segment";
import {SegmentService} from "../../../_services/segment.service";

@Component({
  selector: 'app-did-event',
  templateUrl: './did-event.component.html',
  styleUrls: ['./did-event.component.css']
})
export class DidEventComponent implements OnInit {
  registeredEvents:RegisteredEvent[] = [];
  a:any;

  constructor(private daterangepickerOptions: DaterangepickerConfig, private segmentService: SegmentService) {
    this.daterangepickerOptions.settings = {
      locale: {format: 'YYYY-MM-DD'},
      alwaysShowCalendars: false
    };
    this.singleDate = Date.now();
    this.registeredEvents = this.segmentService.getSampleEvents();
    // console.log(this.registeredEvents);

  }
  eventNameChanged(val:any){
    if (val=="Added to Cart"){
      this.a=true;
    }
    else {
      this.a=false;
    }
  }
  passProperties() {
    if(this.a){
      return this.registeredEvents[0].properties;
    }
    else {
      return this.registeredEvents[1].properties;
    }
  }

  hideElementDatepicker = false;
  hideElementDaterangepicker = true;
  hideElementDaySelector = true;
  removeElement = false;
  days = [];


  public singleDate: any;

  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };


  ngOnInit() {
    for (let i = 1; i <= 30; i++) {
      this.days.push(i);
    }
  }

  singleSelect(value: any) {
    this.singleDate = value.start;
  }

  myDateRangePickerOptions: IMyDrpOptions = {
    dateFormat: 'dd.mm.yyyy',
    height: '30px'
  };
  model: any = {
    beginDate: {year: 2018, month: 10, day: 9},
    endDate: {year: 2018, month: 10, day: 19}
  };

  dropdownChanged(val: any) {
    if (val == 'Between') {
      this.hideElementDaterangepicker = false;
      this.hideElementDatepicker = true;
      this.hideElementDaySelector = true;
    }
    else if (val == 'In the Past') {
      this.hideElementDaySelector = false;
      this.hideElementDatepicker = true;
      this.hideElementDaterangepicker = true;
    }
    else {
      this.hideElementDaterangepicker = true;
      this.hideElementDatepicker = false;
      this.hideElementDaySelector = true;
    }

  }

  hideElementInput = true;

  whereDropdown(val: any) {
    if (val == '≏  (Between)') {
      this.hideElementInput = false;
    }
    else {
      this.hideElementInput = true;
    }
  }

  hidePropertySumFilter = true;

  countDropdown(val: any) {
    if (val == 'Property Sum Of') {
      this.hidePropertySumFilter = false;
    }
    else {
      this.hidePropertySumFilter = true;
    }
  }
}
