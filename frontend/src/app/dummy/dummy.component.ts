import { Component, OnInit } from '@angular/core';
import {Http, Response, RequestOptions, Headers} from '@angular/http';

@Component({
  selector: 'app-dummy',
  templateUrl: './dummy.component.html',
  styleUrls: ['./dummy.component.css']
})
export class DummyComponent implements OnInit {

  constructor(private http: Http) { 
  
  }

  ngOnInit() {
    
  }
  getData(){
    console.log("yahan");
    this.http.get('https://userndot-a528b.firebaseio.com/code.json')
    .subscribe(
      (res:Response)=> {
				const data = res.json();
				console.log(data);
					}
			);
  }

}
