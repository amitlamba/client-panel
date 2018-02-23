import { Component,
        OnInit,
        ComponentFactoryResolver,
        Type,
        ViewChild,
        ViewContainerRef } from '@angular/core';
import {DidEventComponent} from "./did-event/did-event.component";


@Component({
  selector: 'app-did-events',
  templateUrl: './did-events.component.html',
  styleUrls: ['./did-events.component.css']
})
export class DidEventsComponent implements OnInit {
  @ViewChild('container', {read: ViewContainerRef}) container: ViewContainerRef;
  components = [];
  didEventClass = DidEventComponent;
  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }
  ngOnInit(){}
  addComponent(componentClass: Type<any>) {
    // Create component dynamically inside the ng-template
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentClass);
    const component = this.container.createComponent(componentFactory);

    // Push the component so that we can keep track of which components are created
    this.components.push(component);
  }
  removeComponent(componentClass: Type<any>) {
    console.log(componentClass);
    // Find the component
    const component = this.components.find((component) => component.instance instanceof componentClass);
    const componentIndex = this.components.indexOf(component);

    if (componentIndex !== -1) {
      // Remove component from both view and array
      this.container.remove(this.container.indexOf(component));
      this.components.splice(componentIndex, 1);
    }
  }
}
