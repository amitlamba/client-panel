import {ComponentFactoryResolver, ViewContainerRef} from '@angular/core';
import {Component, OnInit, ViewChild} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {DateTimeComponent} from "./date-time/date-time.component";
import {
  Campaign, CampaignTime, CampaignType, Now, Schedule1, ScheduleEnd, ScheduleEndType, ScheduleMultipleDates,
  ScheduleOneTime, ScheduleRecurring,
  ScheduleType
} from "../../_models/campaign";
import {CronOptions} from "../../cron-editor/CronOptions";
import {TemplatesService} from "../../_services/templates.service";
import {SmsTemplate} from "../../_models/sms";
import {Segment} from "../../_models/segment";
import * as moment from "moment";
import {ActivatedRoute, Router} from "@angular/router";
import {Email, EmailTemplate} from "../../_models/email";
import {CampaignService} from "../../_services/campaign.service";

@Component({
  selector: 'app-setup-campaign',
  templateUrl: './setup-campaign.component.html',
  styleUrls: ['./setup-campaign.component.css']
})
export class SetupCampaignComponent implements OnInit {
  currentPath: string;
  showScheduleForm: boolean = false;
  showCloseButton: boolean = false;
  disableSubmit: boolean = false;
  occurencesValueFalse: boolean = false;
  cronExpression = '4 3 2 1 1/1 ? *';
  isCronDisabled: boolean = false;
  schedule1: Schedule1 = new Schedule1();
  scheduleType: ScheduleType = ScheduleType.oneTime;

  //Campaign
  smsTemplatesList: SmsTemplate[] = [];
  emailTemplatesList: EmailTemplate[] = [];
  campaign: Campaign = new Campaign();
  campaignName: string = "";
  segmentsList: Segment[] = [];


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

  @ViewChild('parent', {read: ViewContainerRef}) container: ViewContainerRef;

  constructor(private _cfr: ComponentFactoryResolver,
              public segmentService: SegmentService,
              private templatesService: TemplatesService,
              private route: ActivatedRoute,
              private router: Router,
              private campaignService: CampaignService) {
    this.schedule1.oneTime = new ScheduleOneTime();
    this.schedule1.oneTime.nowOrLater = Now.Now;
    this.schedule1.oneTime.campaignTime = new CampaignTime();
    this.currentPath = this.route.snapshot.url[0].path;
  }

  ngOnInit() {
    // Segments List
    this.segmentService.getSegments().subscribe(
      (segments) => {
        this.segmentService.segments = segments;
        this.segmentsList = this.segmentService.segments;
      }
    );
    // SmsTemplates List
    if (this.currentPath === 'sms') {
      this.templatesService.getSmsTemplates().subscribe(
        (response) => {
          this.smsTemplatesList = response;
        }
      );
    }
    // EmailTemplates List
    else {
      this.templatesService.getEmailTemplates().subscribe(
        (response) => {
          this.emailTemplatesList = response;
        }
      );
    }
  }

  continueToSchedule(): void {
    this.showScheduleForm = true;
  }

  onSubmit(): void {
    if (this.scheduleType === "recurring") {
      this.schedule1.recurring.cronExpression = this.cronExpression;
    }
    this.campaign.name = this.campaignName;
    this.campaign.schedule = this.schedule1;
    if (this.currentPath === 'sms') {
      this.campaign.campaignType = CampaignType.SMS;
    }
    else {
      this.campaign.campaignType = CampaignType.EMAIL;
    }
    // console.log(JSON.stringify(this.campaign));
    this.campaignService.saveCampaign(this.campaign).subscribe(
      (campaign) => {
        this.campaignService.campaigns.push(campaign);
        this.router.navigate(["/campaigns"]);
      }
    );
  }

  saveSegmentID(segmentID: number): void {
    this.campaign.segmentationID = segmentID;
  }

  saveTemplateID(templateID: number): void {
    this.campaign.templateID = templateID;
  }

  campaignStartDateSelect(value: any): void {
    this.schedule1.recurring.scheduleStartDate = moment(value.end.valueOf()).format("YYYY-MM-DD");
  }

  campaignEndDateSelect(value: any): void {
    this.schedule1.recurring.scheduleEnd.endsOn = moment(value.end.valueOf()).format("YYYY-MM-DD");
    if (this.schedule1.recurring.scheduleEnd.endsOn >= this.schedule1.recurring.scheduleStartDate) {
      this.disableSubmit = false;
    }
    else {
      this.disableSubmit = true;
    }
  }

  addAnotherDateTime(): void {
    this.showCloseButton = true;
    // check and resolve the component
    var comp = this._cfr.resolveComponentFactory(DateTimeComponent);
    // Create component inside container
    var dateTimeComponent = this.container.createComponent(comp);
    dateTimeComponent.instance._ref = dateTimeComponent;
    dateTimeComponent.instance.showCloseButton = this.showCloseButton;
    dateTimeComponent.instance.campaignTimesList = this.schedule1.multipleDates.campaignDateTimeList;
  }

  makeOneTimeDateObject() {
    if (this.scheduleType !== ScheduleType.oneTime) {
      this.scheduleType = ScheduleType.oneTime;
      this.schedule1 = new Schedule1();
      this.schedule1.oneTime = new ScheduleOneTime();
      this.schedule1.oneTime.nowOrLater = Now.Now;
      this.schedule1.oneTime.campaignTime = new CampaignTime();
    }
  }

  makeMultipleDateObject() {
    if (this.scheduleType !== ScheduleType.multipleDates) {
      this.scheduleType = ScheduleType.multipleDates;
      this.schedule1 = new Schedule1();
      this.schedule1.multipleDates = new ScheduleMultipleDates();
      this.schedule1.multipleDates.campaignDateTimeList = new Array<CampaignTime>();
    }
  }

  makeRecurringObject() {
    if (this.scheduleType !== ScheduleType.recurring) {
      this.scheduleType = ScheduleType.recurring;
      this.schedule1 = new Schedule1();
      this.schedule1.recurring = new ScheduleRecurring();
      this.schedule1.recurring.scheduleStartDate = moment(Date.now()).format("YYYY-MM-DD");
      this.schedule1.recurring.scheduleEnd = new ScheduleEnd();
      this.schedule1.recurring.scheduleEnd.endType = ScheduleEndType.NeverEnd;
      this.schedule1.recurring.scheduleEnd.endsOn = moment(Date.now()).format("YYYY-MM-DD");
      this.schedule1.recurring.cronExpression = this.cronExpression;
      this.schedule1.recurring.scheduleEnd.occurrences = 2;
    }

  }

  checkOccurencesValue() {
    if (this.schedule1.recurring.scheduleEnd.occurrences > 1000 || this.schedule1.recurring.scheduleEnd.occurrences < 1) {
      this.occurencesValueFalse = true;
      this.disableSubmit = true;
    }
    else {
      this.occurencesValueFalse = false;
      this.disableSubmit = false;
    }
  }

}
















