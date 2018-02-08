import {Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {City, Country, GlobalFilterItem, State} from "../../_models/segment";
import {GlobalFilterComponent} from "./global-filter/global-filter.component";

@Component({
  selector: 'app-global-filters',
  templateUrl: './global-filters.component.html',
  styleUrls: ['./global-filters.component.css']
})
export class GlobalFiltersComponent implements OnInit {

  @ViewChild('container', {read: ViewContainerRef}) container: ViewContainerRef;
  components = [];

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
  }

  addComponent() {
    // Create component dynamically inside the ng-template
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(GlobalFilterComponent);
    const component = this.container.createComponent(componentFactory);

    component.instance._ref = component;
    component.instance._parentComponentsArr = this.components;

    // Push the component so that we can keep track of which components are created
    this.components.push(component);
  }
}
