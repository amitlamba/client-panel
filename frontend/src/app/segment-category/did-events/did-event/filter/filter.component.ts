import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  hideInputNumber = false;
  hideInputBetween = true;
  constructor() { }

  ngOnInit() {
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
  hideEqualityDropdown=false;
  filterSecondDropdown(val:any){
    if (val=='Delivery Date'){
        this.hideEqualityDropdown=true;
    }
    else{
      this.hideEqualityDropdown=false;
    }
  }

}
