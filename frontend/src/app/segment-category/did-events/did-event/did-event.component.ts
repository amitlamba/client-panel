import {Component, ComponentFactoryResolver, Input, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {DaterangepickerConfig} from "ng2-daterangepicker";
import {IMyDrpOptions} from 'mydaterangepicker';
import {RegisteredEvent, RegisteredEventProperties} from "../../../_models/segment";
import {SegmentService} from "../../../_services/segment.service";
import {FilterComponent} from "./filter/filter.component";
import index from "@angular/cli/lib/cli";

@Component({
  selector: 'app-did-event',
  templateUrl: './did-event.component.html',
  styleUrls: ['./did-event.component.css']
})
export class DidEventComponent implements OnInit {

  @Input() hideWhere: boolean = false;

  registeredEvents:RegisteredEvent[] = [];
  defaultProperties: RegisteredEventProperties[];
  eventProperties: RegisteredEventProperties[];
  hideElementDatepicker = false;
  hideElementDaterangepicker = true;
  hideElementDaySelector = true;
  removeElement = false;
  days = [];
  hideElementInput = true;
  public singleDate: any;
  eventSelected: boolean = false;

  _ref:any;
  _parentComponentsArr: any[];
  @ViewChild('container', {read: ViewContainerRef}) container: ViewContainerRef;
  filterComponents = [];

  removeObject(){
    this.removeFromParentArr();
    this._ref.destroy();
  }

  removeFromParentArr() {
    // Find the component
    const componentIndex = this._parentComponentsArr.indexOf(this._ref);

    if (componentIndex !== -1) {
      // Remove component from both view and array
      this._parentComponentsArr.splice(componentIndex, 1);
    }
  }

  constructor(private daterangepickerOptions: DaterangepickerConfig, private segmentService: SegmentService,
              private componentFactoryResolver: ComponentFactoryResolver) {
    this.daterangepickerOptions.settings = {
      locale: {format: 'YYYY-MM-DD'},
      alwaysShowCalendars: false
    };
    this.singleDate = Date.now();
    this.registeredEvents = this.segmentService.sampleEvents;
    // console.log(this.registeredEvents);
    this.defaultProperties = this.segmentService.defaultEventProperties;
    this.eventProperties = this.registeredEvents[0].properties;
  }

  eventNameChanged(val:any){
    this.eventProperties = this.registeredEvents[val].properties;
    this.removeAllPropertyFilters();
    this.eventSelected = true;
  }

  addPropertyFilter() {
    // Create component dynamically inside the ng-template
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(FilterComponent);
    const component = this.container.createComponent(componentFactory);

    component.instance._ref = component;
    component.instance.defaultProperties = this.defaultProperties;
    component.instance.eventProperties = this.eventProperties;
    component.instance._parentComponentsArr = this.filterComponents;

    // Push the component so that we can keep track of which components are created
    this.filterComponents.push(component);
  }

  removeAllPropertyFilters() {
    for(let fc of this.filterComponents) {
      fc.destroy();
    }
    this.filterComponents=[];
  }

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
