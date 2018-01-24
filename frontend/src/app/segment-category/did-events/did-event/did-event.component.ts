import {Component, OnInit} from '@angular/core';
import {DaterangepickerConfig} from "ng2-daterangepicker";
import {IMyDrpOptions} from 'mydaterangepicker';

@Component({
  selector: 'app-did-event',
  templateUrl: './did-event.component.html',
  styleUrls: ['./did-event.component.css']
})
export class DidEventComponent implements OnInit {

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

  constructor(private daterangepickerOptions: DaterangepickerConfig) {
    this.daterangepickerOptions.settings = {
      locale: {format: 'YYYY-MM-DD'},
      alwaysShowCalendars: false
    };
    this.singleDate = Date.now();
  }

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
