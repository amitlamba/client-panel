import {ComponentFactoryResolver, ViewContainerRef} from '@angular/core';
import {Component, OnInit, ViewChild} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {DateTimeComponent} from "./date-time/date-time.component";
import {
  Campaign, CampaignTime, CampaignType, Now, Schedule, ScheduleEnd, ScheduleEndType,
  ScheduleType
} from "../../_models/campaign";
import {CronOptions} from "../../cron-editor/CronOptions";
import {TemplatesService} from "../../_services/templates.service";
import {SmsTemplate} from "../../_models/sms";
import {Segment} from "../../_models/segment";
import * as moment from "moment";
import {ActivatedRoute} from "@angular/router";
import {Email, EmailTemplate} from "../../_models/email";

@Component({
  selector: 'app-setup-campaign',
  templateUrl: './setup-campaign.component.html',
  styleUrls: ['./setup-campaign.component.css']
})
export class SetupCampaignComponent implements OnInit {
  currentPath: string;
  showScheduleForm: boolean = false;
  showCloseButton: boolean = false;
  cronExpression = '4 3 2 1 1/1 ? *';
  isCronDisabled: boolean = false;
  smsTemplatesList: SmsTemplate[] = [];
  emailTemplatesList: EmailTemplate[] = [];
  schedule: Schedule = new Schedule();
  campaign: Campaign = new Campaign();

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

  //Date Picker Options
  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };

  //Campaign Name
  campaignName: string = "";
  segmentsList: Segment[] = [];

  @ViewChild('parent', {read: ViewContainerRef}) container: ViewContainerRef;

  constructor(private _cfr: ComponentFactoryResolver,
              public segmentService: SegmentService,
              private templatesService: TemplatesService,
              private route: ActivatedRoute) {
    this.schedule.scheduleType = ScheduleType.oneTime;
    this.schedule.startTime = Now.Now;
    this.schedule.campaignTimeList = new Array<CampaignTime>();
    this.schedule.startDateTime = moment(Date.now()).format("YYYY-MM-DD");
    this.schedule.scheduleEnd = new ScheduleEnd();
    this.schedule.scheduleEnd.endType = ScheduleEndType.NeverEnd;
    this.schedule.scheduleEnd.endsOn = moment(Date.now()).format("YYYY-MM-DD");
    this.currentPath = this.route.snapshot.url[0].path;
  }

  ngOnInit() {
    //Segments List
    for (let i = 0; i < this.segmentService.segments.length; i++) {
      this.segmentsList.push(this.segmentService.segments[i]);
    }
    //SmsTemplates List
    this.templatesService.getSmsTemplates().subscribe(
      (response) => {
        for (let i = 0; i < response.length; i++) {
          this.smsTemplatesList.push(response[i]);
        }
      }
    );

    //EmailTemplates List
    this.templatesService.getEmailTemplates().subscribe(
      (response) => {
        for (let i = 0; i < response.length; i++) {
          this.emailTemplatesList.push(response[i]);
        }
      }
    );
  }

  continueToSchedule(): void {
    this.showScheduleForm = true;
  }

  onSubmit(): void {
    this.schedule.cronExpression = this.cronExpression;
    this.campaign.name = this.campaignName;
    this.campaign.schedule = this.schedule;
    if(this.currentPath==='sms'){
      this.campaign.campaignType = CampaignType.SMS;
    }
    else{
      this.campaign.campaignType = CampaignType.EMAIL;
    }
    console.log(JSON.stringify(this.campaign));
  }

  saveSegmentID(segmentID: number): void {
    this.campaign.segmentationID = segmentID;
  }

  saveTemplateID(templateID: number): void {
    this.campaign.templateID = templateID;
  }

  campaignStartDateSelect(value: any): void {
    this.schedule.startDateTime = moment(value.end.valueOf()).format("YYYY-MM-DD");
  }

  campaignEndDateSelect(value: any): void {
    this.schedule.scheduleEnd.endsOn = moment(value.end.valueOf()).format("YYYY-MM-DD");
  }

  addAnotherDateTime(): void {
    this.showCloseButton = true;
    // check and resolve the component
    var comp = this._cfr.resolveComponentFactory(DateTimeComponent);
    // Create component inside container
    var dateTimeComponent = this.container.createComponent(comp);
    dateTimeComponent.instance._ref = dateTimeComponent;
    dateTimeComponent.instance.showCloseButton = this.showCloseButton;
    dateTimeComponent.instance.campaignTimes = this.schedule.campaignTimeList;
  }

  emptyCampaignTimesArray(): void {
    this.schedule.campaignTimeList = [];
  }
}
















