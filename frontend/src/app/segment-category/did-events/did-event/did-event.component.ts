import {
  Component, ComponentFactoryResolver, EventEmitter, Input, OnInit, Output, ViewChild,
  ViewContainerRef
} from '@angular/core';
import {DaterangepickerConfig} from "ng2-daterangepicker";
import {IMyDrpOptions} from 'mydaterangepicker';
import {
  DateFilter, DateOperator, Event, NumberOperator, PropertyFilter, RegisteredEvent,
  RegisteredEventProperties, WhereFilter, WhereFilterName
} from "../../../_models/segment";
import {SegmentService} from "../../../_services/segment.service";
import {FilterComponent} from "./filter/filter.component";
import index from "@angular/cli/lib/cli";
import {DidEventsComponent} from "../did-events.component";

@Component({
  selector: 'app-did-event',
  templateUrl: './did-event.component.html',
  styleUrls: ['./did-event.component.css']
})
export class DidEventComponent implements OnInit {

  @Input() hideWhere: boolean = false;

  private localDidEvent: Event;
  @Input() get didEvent(): Event {
    return this.localDidEvent;
  }

  set didEvent(didEvent: Event) {
    this.localDidEvent = didEvent;
    this.didEventChange.emit(this.localDidEvent);
  }

  // the name is an Angular convention, @Input variable name + "Change" suffix
  @Output() didEventChange = new EventEmitter();

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
  eventSelected: boolean = true;

  _ref:any;
  _parentRef: DidEventsComponent;
  @ViewChild('container', {read: ViewContainerRef}) container: ViewContainerRef;
  components = [];

  removeObject(){
    this.removeFromParentArr();
    this._ref.destroy();
  }

  removeFromParentArr() {
    // Find the component
    const componentIndex = this._parentRef.components.indexOf(this._ref);

    if (componentIndex !== -1) {
      // Remove component from both view and array
      this._parentRef.components.splice(componentIndex, 1);
    }

    const index = this._parentRef.didEvents.events.indexOf(this.didEvent);
    if (index != -1) {
      this._parentRef.didEvents.events.splice(index, 1);
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
    this.didEvent.name = this.registeredEvents[val].name;
    this.didEvent.dateFilter = new DateFilter();
    this.didEvent.dateFilter.operator = DateOperator.Before;
    this.didEvent.dateFilter.values = [];
    this.didEvent.propertyFilters = [];
    this.didEvent.whereFilter = new WhereFilter();
    this.didEvent.whereFilter.operator = NumberOperator.GreaterThan;
    this.didEvent.whereFilter.values = [];
    this.countDropdown("Count");
  }

  addPropertyFilter() {
    // Create component dynamically inside the ng-template
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(FilterComponent);
    const component = this.container.createComponent(componentFactory);

    component.instance._ref = component;
    component.instance.defaultProperties = this.defaultProperties;
    component.instance.eventProperties = this.eventProperties;
    component.instance._parentRef = this;

    var propertyFilter = new PropertyFilter();
    this.didEvent.propertyFilters.push(propertyFilter);
    component.instance.propertyFilter = propertyFilter;

    // Push the component so that we can keep track of which components are created
    this.components.push(component);
  }

  removeAllPropertyFilters() {
    for(let fc of this.components) {
      fc.destroy();
    }
    this.components=[];
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
    this.eventNameChanged(0);
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
    if (val == 'SumOfValuesOf') {
      this.localDidEvent.whereFilter.whereFilterName = WhereFilterName.SumOfValuesOf;
      this.hidePropertySumFilter = false;
    }
    else {
      this.localDidEvent.whereFilter.whereFilterName = WhereFilterName.Count;
      this.hidePropertySumFilter = true;
    }
  }

  getNumberTypeEventProperties(): RegisteredEventProperties[] {
    return this.eventProperties.filter((data)=>{return data.dataType=='number'});
  }
}
