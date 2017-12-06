import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  moduleId: module.id,
  selector: 'app',
  templateUrl: 'app.component.html'
})
export class AppComponent {

  constructor(private http: HttpClient) {
  }

  getData() {
  }

}
