import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from './header/index';
import {Http, Response, RequestOptions, Headers} from '@angular/http';

@Component({
    moduleId: module.id,
    selector: 'app',
    templateUrl: 'app.component.html'
})
export class AppComponent {
    
    constructor(private http:Http){}
    getData(){
    this.http.get('https://userndot-a528b.firebaseio.com/code.json')
    .subscribe(
      (res:Response)=> {
                const data = res.json();
                console.log(data);
                    }
            );
 }

}
