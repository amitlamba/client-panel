import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

@Injectable()
export class RegisterService {

  constructor(private http: Http) { }
  getData() {
    return this.http.get('https://userndot-a528b.firebaseio.com/code.json');
  }
}
