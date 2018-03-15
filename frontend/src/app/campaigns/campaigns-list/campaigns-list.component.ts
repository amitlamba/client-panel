import {Component, OnInit} from '@angular/core';
import {IMyDrpOptions} from "mydaterangepicker";
import {CampaignService} from "../../_services/campaign.service";
import {Campaign} from "../../_models/campaign";

@Component({
  selector: 'app-campaigns-list',
  templateUrl: './campaigns-list.component.html',
  styleUrls: ['./campaigns-list.component.css']
})
export class CampaignsListComponent implements OnInit {

  campaigns: Campaign[];

  constructor(private campaignService: CampaignService) {
  }

  ngOnInit() {
    this.campaignService.getCampaignList().subscribe((campaigns) => {
      this.campaigns = campaigns;
      console.log(campaigns);
    });

  }

  // Date Range Input Code
  myDateRangePickerOptions: IMyDrpOptions = {
    dateFormat: 'dd.mm.yyyy',
    height: '30px'
  };
  public model: any = {
    beginDate: {year: 2018, month: 10, day: 9},
    endDate: {year: 2018, month: 10, day: 19}
  };

}
