import {Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {GeographyFilterComponent} from "./geography-filter/geography-filter.component";

@Component({
  selector: 'app-geography-filters',
  templateUrl: './geography-filters.component.html',
  styleUrls: ['./geography-filters.component.css']
})
export class GeographyFiltersComponent implements OnInit {

  @ViewChild('container', {read: ViewContainerRef}) container: ViewContainerRef;
  components = [];

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
  }

  addComponent() {
    // Create component dynamically inside the ng-template
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(GeographyFilterComponent);
    const component = this.container.createComponent(componentFactory);

    component.instance._ref = component;
    component.instance._parentComponentsArr = this.components;

    // Push the component so that we can keep track of which components are created
    this.components.push(component);
  }
}
