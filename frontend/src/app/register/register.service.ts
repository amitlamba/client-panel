import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  getData() {
    return this.http.get('https://userndot-a528b.firebaseio.com/code.json')
      .map((res: Response) => res.json());
  }
}
