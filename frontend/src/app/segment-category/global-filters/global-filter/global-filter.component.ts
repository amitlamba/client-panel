import { Component, OnInit } from '@angular/core';
import {SegmentService} from "../../../_services/segment.service";

@Component({
  selector: 'app-global-filter',
  templateUrl: './global-filter.component.html',
  styleUrls: ['./global-filter.component.css']
})
export class GlobalFilterComponent implements OnInit {

  globalFilters: any;
  firstDropDown: string[];
  secondDropDown: any[];
  firstFilterSelected: string;
  secondFilterSelected: string;
  secondFilterDataType: string;
  options: any[];
  maxOrder: number = 0;

  _parentComponentsArr: any[];
  _ref: any;

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
    this.secondDropDown = this.getDropdownList(1);
    this.maxOrder = 1;
  }

  secondFilterChanged(name: string) {
    this.secondFilterSelected = name;
    for(var filter in this.globalFilters[this.firstFilterSelected]) {
      if(filter["propertyName"] == this.secondFilterSelected) {
        this.secondFilterDataType = filter["propertyType"];
        this.options = filter["options"];
      }
    }
    this.maxOrder = 2;
  }


  getDropdownList(order: number): any[] {
    switch (order) {
      case 0:
        return Object.keys(this.globalFilters);
      case 1:
        // if(this.firstFilterSelected == "Geography")
        //   return this.segmentService.getCountries().subscribe();
        var filters = this.globalFilters[this.firstFilterSelected];
        return filters;
    }
  }
}
