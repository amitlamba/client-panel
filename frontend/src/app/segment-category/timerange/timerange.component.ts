import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-timerange',
  templateUrl: './timerange.component.html',
  styleUrls: ['./timerange.component.scss']
})
export class TimerangeComponent implements OnInit {

  timeOfTheDayOptions;
  @Output() values = new EventEmitter();
  fromHours: string;
  toHours: string;
  fromMinutes: string;
  toMinutes: string;

  constructor() {
  }

  @Input() set options(options: any) {
    this.timeOfTheDayOptions = options;
  }

  ngOnInit() {
    console.log(this.timeOfTheDayOptions);
    this.setDefaultTimeValues();
  }

  setDefaultTimeValues() {
    this.fromHours = this.timeOfTheDayOptions[0].hours[4];
    this.fromMinutes = this.timeOfTheDayOptions[1].minutes[7];
    this.toHours = this.timeOfTheDayOptions[0].hours[6];
    this.toMinutes = this.timeOfTheDayOptions[1].minutes[3];
    this.timeStringChanged();
  }

  timeStringChanged() {
    console.log("From Hours" + this.fromHours);
    console.log("From Minutes" + this.fromMinutes);
    console.log("to Hours" + this.toHours);
    console.log("To Minutes" + this.toMinutes);
    let hoursArray: string[] = this.timeOfTheDayOptions[0].hours;
    if (hoursArray.indexOf(this.fromHours) > hoursArray.indexOf(this.toHours)) {
      alert("Bro! , The value of From time should be less than To Time");
    }
    let fromTimeString: string = this.fromHours + ":" + this.fromMinutes;
    let toTimestring: string = this.toHours + ":" + this.toMinutes;
    this.values.emit([fromTimeString, toTimestring]);
  }
}
