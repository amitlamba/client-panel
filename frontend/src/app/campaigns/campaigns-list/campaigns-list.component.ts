import { Component, OnInit } from '@angular/core';
import {IMyDrpOptions} from "mydaterangepicker";

@Component({
  selector: 'app-campaigns-list',
  templateUrl: './campaigns-list.component.html',
  styleUrls: ['./campaigns-list.component.css']
})
export class CampaignsListComponent implements OnInit {

  constructor() { }

  ngOnInit() {
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
