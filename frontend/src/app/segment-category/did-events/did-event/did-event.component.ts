import {Component, OnInit} from '@angular/core';
import {DaterangepickerConfig} from "ng2-daterangepicker";
import {IMyDrpOptions} from 'mydaterangepicker';
import {RegisteredEvent, RegisteredEventProperties} from "../../../_models/segment";

@Component({
  selector: 'app-did-event',
  templateUrl: './did-event.component.html',
  styleUrls: ['./did-event.component.css']
})
export class DidEventComponent implements OnInit {
  registeredEvents:RegisteredEvent[] = [];
  a:any;

  constructor(private daterangepickerOptions: DaterangepickerConfig) {
    this.daterangepickerOptions.settings = {
      locale: {format: 'YYYY-MM-DD'},
      alwaysShowCalendars: false
    };
    this.singleDate = Date.now();

    var registeredEvent = new RegisteredEvent();
    registeredEvent.name="Added to Cart";
    var registeredEvent2 = new RegisteredEvent();
    registeredEvent2.name="UTM Visited";

    registeredEvent.properties = [];
    registeredEvent2.properties = [];

    var property1 = new RegisteredEventProperties();
    property1.name="Event Property";
    registeredEvent.properties.push(property1);

    var property2 = new RegisteredEventProperties();
    property2.name="First Time";
    registeredEvent.properties.push(property2);
    registeredEvent2.properties.push(property2);
    var property3 = new RegisteredEventProperties();
    property3.name="Time of the day";
    registeredEvent.properties.push(property3);
    registeredEvent2.properties.push(property3);
    var property4 = new RegisteredEventProperties();
    property4.name="Day of the week";
    registeredEvent.properties.push(property4);
    registeredEvent2.properties.push(property4);
    var property5 = new RegisteredEventProperties();
    property5.name="Day of the month";
    registeredEvent.properties.push(property5);
    registeredEvent2.properties.push(property5);

    var property6 = new RegisteredEventProperties();
    property6.name="UTM Source";
    registeredEvent2.properties.push(property6);
    var property7 = new RegisteredEventProperties();
    property7.name="Session Referrer";
    registeredEvent2.properties.push(property7);

    this.registeredEvents.push(registeredEvent);
    this.registeredEvents.push(registeredEvent2);
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

  private singleSelect(value: any) {
    this.singleDate = value.start;
  }

  myDateRangePickerOptions: IMyDrpOptions = {
    dateFormat: 'dd.mm.yyyy',
    height: '30px'
  };
  private model: any = {
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
