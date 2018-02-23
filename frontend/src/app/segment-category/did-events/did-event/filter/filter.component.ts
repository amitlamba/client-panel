import {Component, Input, OnInit} from '@angular/core';
import {RegisteredEventProperties} from "../../../../_models/segment";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
  host: { '[class]': 'dropdownSettings.classes' },
})
export class FilterComponent implements OnInit {
  hideInputNumber = false;
  hideInputBetween = true;
  eventProperty=false;
  dropdownSettings={};

  timeOfTheDay=false;

  firstTimeList=[];
  firstTime=false;

  dayOfTheWeekList=[];
  dayOfTheWeek=false;

  formModel = {
    skills:[]
  };
  formModel1 = {
    skills:[]
  };
  @Input() properties:RegisteredEventProperties[]=[];

  constructor() {

  }

  ngOnInit() {
    // console.log(this.properties);
    this.firstTimeList = [
      {"id":1,"itemName":"Yes"}
    ];
    this.dayOfTheWeekList = [
      {"id":1,"itemName":"Sunday"},
      {"id":2,"itemName":"Monday"},
      {"id":3,"itemName":"Tuesday"},
      {"id":4,"itemName":"Wednesday"},
      {"id":5,"itemName":"Thursday"},
      {"id":6,"itemName":"Friday"},
      {"id":7,"itemName":"Saturday"}
    ];
    this.dropdownSettings = {
      singleSelection: false,
      text:"Options",
      selectAllText:'Select All',
      unSelectAllText:'UnSelect All',
      enableSearchFilter: false,
      classes:"custom-class"
    };
  }

  filterFirstDropdown(val:any){
    if (val=="Event Property"){
      this.eventProperty=true;
      this.timeOfTheDay=false;
      this.firstTime=false;
      this.dayOfTheWeek=false;
    }
    else if (val=="Time of the day") {
      this.timeOfTheDay=true;
      this.eventProperty=false;
      this.firstTime=false;
      this.dayOfTheWeek=false;
    }
    else if(val=="First Time") {
      this.firstTime=true;
      this.timeOfTheDay=false;
      this.eventProperty=false;
      this.dayOfTheWeek=false;
    }
    else if(val=="Day of the week"){
      this.dayOfTheWeek=true;
      this.timeOfTheDay=false;
      this.eventProperty=false;
      this.firstTime=false;
    }
  }

  filterDropdown(val:any) {
    if(val =='∃  (exists)' || val == '∄  (does not exists)'){
      this.hideInputNumber=true;
      this.hideInputBetween=true;
    }
    else if (val=='≏  (Between)'){
      this.hideInputNumber=false;
      this.hideInputBetween=false;
    }
    else {
      this.hideInputBetween=true;
      this.hideInputNumber=false;
    }
  }
  // hideEqualityDropdown=false;
  // filterSecondDropdown(val:any){
  //   if (val=='Delivery Date'){
  //       this.hideEqualityDropdown=true;
  //   }
  //   else{
  //     this.hideEqualityDropdown=false;
  //   }
  // }

}
