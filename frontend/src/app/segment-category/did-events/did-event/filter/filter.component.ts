import {
  AfterContentInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, ViewChild,
  ViewContainerRef
} from '@angular/core';
import {PropertyFilter, PropertyType, RegisteredEventProperties} from "../../../../_models/segment";
import {NgForm} from "@angular/forms";
import {SegmentService} from "../../../../_services/segment.service";
import {iterator} from "rxjs/symbol/iterator";
import {DidEventComponent} from "../did-event.component";

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
  host: { '[class]': 'dropdownSettings.classes' },
})
export class FilterComponent implements OnInit {

  @ViewChild('filterWidget') filterWidget: ViewContainerRef;

  selectedProperty: RegisteredEventProperties = null;

  dropdownSettings={};

  formModel1 = {
    skills:[]
  };
  @Input() eventProperties:RegisteredEventProperties[]=[];
  @Input() defaultProperties:RegisteredEventProperties[]=[];

  private localPropertyFilter: PropertyFilter;
  @Input() get propertyFilter(): PropertyFilter {
    return this.localPropertyFilter;
  }

  set propertyFilter(propertyFilter: PropertyFilter) {
    if(!propertyFilter.values)
      propertyFilter.values = [];
    this.localPropertyFilter = propertyFilter;
    this.propertyFilterChange.emit(this.localPropertyFilter);
  }

  // the name is an Angular convention, @Input variable name + "Change" suffix
  @Output() propertyFilterChange = new EventEmitter();

  _parentRef:DidEventComponent;
  _ref:any;

  constructor(segmentService: SegmentService) {
  }

  removeObject(){
    this.removeFromParentArr();
    this._ref.destroy();
  }

  removeFromParentArr() {
    // Find the component
    const componentIndex = this._parentRef.components.indexOf(this._ref);

    if (componentIndex !== -1) {
      // Remove component from both view and array
      this._parentRef.components.splice(componentIndex, 1);
    }

    const index = this._parentRef.didEvent.propertyFilters.indexOf(this.propertyFilter);
    if (index != -1) {
      this._parentRef.didEvent.propertyFilters.splice(index, 1);
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
    this.localPropertyFilter.name = this.selectedProperty.name;
    this.localPropertyFilter.type = PropertyType[this.selectedProperty.dataType];
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
    return options;
  }
}
