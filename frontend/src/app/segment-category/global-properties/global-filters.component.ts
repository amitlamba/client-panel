import { Component, OnInit } from '@angular/core';
import {SegmentService} from "../../_services/segment.service";

@Component({
  selector: 'app-global-filters',
  templateUrl: './global-filters.component.html',
  styleUrls: ['./global-filters.component.css']
})
export class GlobalFiltersComponent implements OnInit {

  globalFilters: any;
  firstDropDown: string[];
  secondDropDown: string[];
  firstFilterSelected: string;
  secondFilterSelected: string;

  constructor(private segmentService: SegmentService) {
    this.globalFilters = this.segmentService.globalFilters;
  }

  ngOnInit() {
    this.firstDropDown = Object.keys(this.globalFilters);
  }

  getSecondFilters(): string[] {
    return Object.keys(this.globalFilters[this.firstFilterSelected]);
  }

  firstFilterChanged(name: string) {
    this.firstFilterSelected = name;
    this.secondDropDown = Object.keys(this.globalFilters[this.firstFilterSelected]);
  }
}
