import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'
import {HttpClient} from '@angular/common/http';
import {AppSettings} from "../_settings/app-settings";

@Injectable()
export class AuthenticationService {
  public token: string;

  constructor(private httpClient: HttpClient) {
    // set token if saved in local storage
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  login(username: string, password: string): Observable<boolean> {

    return this.httpClient.post(AppSettings.API_ENDPOINT_AUTH_AUTH, JSON.stringify({username: username, password: password}))
      .map((response: Response) => {
        // login successful if there's a jwt token in the response
        let token = response.json() && response.json().token;
        console.log(response);
        if (token) {
          // set token property
          this.token = token;

          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));

          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      });
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    localStorage.removeItem('currentUser');
  }
}
