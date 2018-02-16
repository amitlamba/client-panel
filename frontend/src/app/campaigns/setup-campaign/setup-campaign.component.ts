import {ComponentFactoryResolver,ViewContainerRef} from '@angular/core';
import {Component, OnInit, ViewChild} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {DateTimeComponent} from "./date-time/date-time.component";
import {CampaignTime, Now, Schedule} from "../../_models/campaign";

@Component({
  selector: 'app-setup-campaign',
  templateUrl: './setup-campaign.component.html',
  styleUrls: ['./setup-campaign.component.css']
})
export class SetupCampaignComponent implements OnInit {
  showScheduleForm=false;
  showCloseButton=false;
  schedule=new Schedule();

  //Date Picker
  public singleDate: any;
  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };

  //Campaign Name And Segment Name
  campaignName:string = "";
  segment:string="";
  segmentsList:any=[];

  @ViewChild('parent', { read: ViewContainerRef })    container: ViewContainerRef;

  constructor(private _cfr: ComponentFactoryResolver,
              public segmentService:SegmentService) {
    this.schedule.scheduleType="oneTime";
    this.schedule.startTime=Now.Now;
    this.schedule.campaignTimeList= new Array<CampaignTime>();
    this.singleDate = Date.now();
  }
  ngOnInit() {
    //Segments List
    for(let i=0;i<this.segmentService.segments.length;i++) {
      this.segmentsList.push(this.segmentService.segments[i].name);
    }
  }
  continueToSchedule(){
    this.showScheduleForm=true;
  }
  onSubmit(){
    console.log(this.campaignName);
    console.log(this.segment);
    console.log(this.schedule);
  }
  addAnotherDateTime(){
    this.showCloseButton=true;
    // check and resolve the component
    var comp = this._cfr.resolveComponentFactory(DateTimeComponent);
    // Create component inside container
    var dateTimeComponent = this.container.createComponent(comp);
    dateTimeComponent.instance._ref = dateTimeComponent;
    dateTimeComponent.instance.showCloseButton=this.showCloseButton;
    dateTimeComponent.instance.campaignTimes = this.schedule.campaignTimeList;
  }
  emptyCampaignTimesArray(){
    this.schedule.campaignTimeList=[];
  }

}
















