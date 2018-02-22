import {ComponentFactoryResolver, ViewContainerRef} from '@angular/core';
import {Component, OnInit, ViewChild} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {DateTimeComponent} from "./date-time/date-time.component";
import {CampaignTime, Now, Schedule, ScheduleEnd, ScheduleEndType, ScheduleType} from "../../_models/campaign";
import {CronOptions} from "../../cron-editor/CronOptions";

@Component({
  selector: 'app-setup-campaign',
  templateUrl: './setup-campaign.component.html',
  styleUrls: ['./setup-campaign.component.css']
})
export class SetupCampaignComponent implements OnInit {
  showScheduleForm = false;
  showCloseButton = false;
  schedule = new Schedule();
  cronExpression = '4 3 2 12 1/1 ? *';
  isCronDisabled = false;
  cronOptions: CronOptions = {
    formInputClass: 'form-control cron-editor-input',
    formSelectClass: 'form-control cron-editor-select',
    formRadioClass: 'cron-editor-radio',
    formCheckboxClass: 'cron-editor-checkbox',

    defaultTime: "10:00:00",

    hideMinutesTab: false,
    hideHourlyTab: false,
    hideDailyTab: false,
    hideWeeklyTab: false,
    hideMonthlyTab: false,
    hideYearlyTab: false,
    hideAdvancedTab: false,

    use24HourTime: false,
    hideSeconds: true
  };

  //Date Picker
  public singleDate: any;
  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };

  //Campaign Name And Segment Name
  campaignName: string = "";
  segment: string = "";
  segmentsList: any = [];

  @ViewChild('parent', {read: ViewContainerRef}) container: ViewContainerRef;

  constructor(private _cfr: ComponentFactoryResolver,
              public segmentService: SegmentService) {
    this.schedule.scheduleType = ScheduleType.oneTime;
    this.schedule.startTime = Now.Now;
    this.schedule.campaignTimeList = new Array<CampaignTime>();
    this.singleDate = Date.now();
    this.schedule.startDateTime = this.singleDate;
    this.schedule.scheduleEnd = new ScheduleEnd();
    this.schedule.scheduleEnd.endType = ScheduleEndType.NeverEnd;
    this.schedule.scheduleEnd.endsOn = this.singleDate;
  }

  ngOnInit() {
    //Segments List
    for (let i = 0; i < this.segmentService.segments.length; i++) {
      this.segmentsList.push(this.segmentService.segments[i].name);
    }
  }

  continueToSchedule() {
    this.showScheduleForm = true;
  }

  onSubmit() {
    console.log(this.campaignName);
    console.log(this.segment);
    console.log(this.schedule);
  }

  campaignStartDateSelect(value: any) {
    this.schedule.startDateTime = value.start.valueOf();
  }

  campaignEndDateSelect(value: any) {
    this.schedule.scheduleEnd.endsOn = value.start.valueOf();
  }

  addAnotherDateTime() {
    this.showCloseButton = true;
    // check and resolve the component
    var comp = this._cfr.resolveComponentFactory(DateTimeComponent);
    // Create component inside container
    var dateTimeComponent = this.container.createComponent(comp);
    dateTimeComponent.instance._ref = dateTimeComponent;
    dateTimeComponent.instance.showCloseButton = this.showCloseButton;
    dateTimeComponent.instance.campaignTimes = this.schedule.campaignTimeList;
  }

  emptyCampaignTimesArray() {
    this.schedule.campaignTimeList = [];
  }

  resetScheduleEnd() {
    // this.schedule.scheduleEnd = new ScheduleEnd();
    // console.log("laksh");
  }
}
















