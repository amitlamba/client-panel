import {Component, Input, OnInit} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {IMyDrpOptions} from "mydaterangepicker";
import {DaterangepickerConfig} from "ng2-daterangepicker";
import * as moment from "moment";

@Component({
  selector: 'app-date-comparator',
  templateUrl: './date-comparator.component.html',
  styleUrls: ['./date-comparator.component.css']
})
export class DateComparatorComponent implements OnInit {

  hideElementDatepicker = false;
  hideElementDaterangepicker = true;
  hideElementDaySelector = true;
  hideWasExactlyDaySelector = true;
  hideWillBeExactlyDaySelector = true;
  removeElement = false;

  @Input("dataType") dataType: string;

  dateComparatorMetadata: any;
  absoluteDateComparatorMetadata: string[];
  relativeDateComparatorMetadata: string[];
  timePeriodVars: string[];

  constructor(public segmentService: SegmentService, private daterangepickerOptions: DaterangepickerConfig) {

    this.dateComparatorMetadata = this.segmentService.dateComparatorMetadata;
    this.absoluteDateComparatorMetadata = Object.keys(this.segmentService.dateComparatorMetadata.Absolute);
    this.relativeDateComparatorMetadata = Object.keys(this.segmentService.dateComparatorMetadata.Relative);
    this.timePeriodVars = Object.keys(this.segmentService.timePeriod);

    this.daterangepickerOptions.settings = {
      locale: {format: 'YYYY-MM-DD'},
      alwaysShowCalendars: false
    };

  }

  ngOnInit() {
  }

  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };

  public multiPicker = {
    singleDatePicker: false,
    showDropdowns: true,
    opens: "center",
    startDate: moment(),
    endDate: moment(),
    ranges: {
      "Today": [moment(), moment()],
      "Yesterday": [moment().subtract("1","day"), moment().subtract("1","day")],
      "Last 7 Days": [moment().subtract("7","day"), moment()],
      "Last 30 Days": [moment().subtract("30","day"), moment()],
      "Last Month": [moment().subtract("1","month"), moment()],
    }
  };

  selectedDate(value, daterange) {
    console.log(value);
    console.log(daterange);
  }

  singleSelect(value: any) {
    console.log(value);
  }

  dropdownChanged(val: string) {
    if (val == 'Between') {
      this.hideElementDaterangepicker = false;
      this.hideElementDatepicker = true;
      this.hideElementDaySelector = true;
      this.hideWasExactlyDaySelector = true;
      this.hideWillBeExactlyDaySelector = true;
    } else if (this.absoluteDateComparatorMetadata.includes(val)) {
      this.hideElementDaterangepicker = true;
      this.hideElementDatepicker = false;
      this.hideElementDaySelector = true;
      this.hideWasExactlyDaySelector = true;
      this.hideWillBeExactlyDaySelector = true;
    } else if (["Today"].includes(val)) {
      this.hideElementDaySelector = true;
      this.hideElementDatepicker = true;
      this.hideElementDaterangepicker = true;
      this.hideWasExactlyDaySelector = true;
      this.hideWillBeExactlyDaySelector = true;
    } else if (["WasExactly"].includes(val)) {
      this.hideElementDaySelector = true;
      this.hideElementDatepicker = true;
      this.hideElementDaterangepicker = true;
      this.hideWasExactlyDaySelector = false;
      this.hideWillBeExactlyDaySelector = true;
    } else if (["WillBeExactly"].includes(val)) {
      this.hideElementDaySelector = true;
      this.hideElementDatepicker = true;
      this.hideElementDaterangepicker = true;
      this.hideWasExactlyDaySelector = true;
      this.hideWillBeExactlyDaySelector = false;
    } else {
      this.hideElementDaySelector = false;
      this.hideElementDatepicker = true;
      this.hideElementDaterangepicker = true;
      this.hideWasExactlyDaySelector = true;
      this.hideWillBeExactlyDaySelector = true;
    }
  }

}
