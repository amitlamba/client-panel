import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-demo-form',
  templateUrl: './demo-form.component.html',
  styleUrls: ['./demo-form.component.scss']
})
export class DemoFormComponent implements OnInit {
  phone_number:string;
  preferredCountries = ['in','us','ru', 'gb'];
  constructor() { }

  ngOnInit() {
  }

}
