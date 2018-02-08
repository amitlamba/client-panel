import {AfterContentInit, Component, Input, OnChanges, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {RegisteredEventProperties} from "../../../../_models/segment";
import {NgForm} from "@angular/forms";
import {SegmentService} from "../../../../_services/segment.service";
import {iterator} from "rxjs/symbol/iterator";

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
  host: { '[class]': 'dropdownSettings.classes' },
})
export class FilterComponent implements OnInit {

  @ViewChild('filterWidget') filterWidget: ViewContainerRef;

  selectedProperty: RegisteredEventProperties = null;

  hideInputNumber = false;
  hideInputBetween = true;
  eventProperty=false;
  dropdownSettings={};

  formModel1 = {
    skills:[]
  };
  @Input() eventProperties:RegisteredEventProperties[]=[];
  @Input() defaultProperties:RegisteredEventProperties[]=[];
  _parentComponentsArr:any[];
  _ref:any;

  constructor(segmentService: SegmentService) {
  }

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

  ngOnInit() {
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
    this.selectedProperty = this.getPropertyByName(val);
  }

  getPropertyByName(propName: string): RegisteredEventProperties {
    for(let prop of this.eventProperties) {
      if(prop.name == propName)
        return prop;
    }
    for(let prop of this.defaultProperties) {
      if(prop.name == propName)
        return prop;
    }
    return null;
  }

  getSelectedPropertyOptions(): any[] {
    let options = this.selectedProperty.options.map((option, index)=>{return {'id': index, 'itemName':option}});
    console.log(options);
    return options;
  }
}
