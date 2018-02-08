import {Component, OnInit, ViewChild} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-setup-campaign',
  templateUrl: './setup-campaign.component.html',
  styleUrls: ['./setup-campaign.component.css']
})
export class SetupCampaignComponent implements OnInit {
  @ViewChild('f') public myForrm:NgForm;
  segmentsList:any=[];
  showScheduleForm=false;
  //Campaign Name And Segment Name
  campaignName:string = "";
  segment:string="";

  //Date Picker
  public singleDate: any;
  public singlePicker = {
    singleDatePicker: true,
    showDropdowns: true,
    opens: "right"
  };

  constructor(public segmentService:SegmentService) {
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
    console.log(this.myForrm.form.value);
  }
  singleSelect(value: any) {
    this.singleDate = value.start;
  }

}
