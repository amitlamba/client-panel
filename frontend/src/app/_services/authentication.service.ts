import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'
import {HttpClient} from '@angular/common/http';
import {AppSettings} from "../_settings/app-settings";
import {RegistrationRequest, UserProfileRequest} from "../_models/client";

@Injectable()
export class AuthenticationService {
  public token: string;

  constructor(private httpClient: HttpClient) {
    // set token if saved in local storage
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  login(username: string, password: string): Observable<any> {

    return this.httpClient.post(AppSettings.API_ENDPOINT_AUTH_AUTH, {username: username, password: password})
      .map((response: any) => {
        console.log(response);
        // login successful if there's a jwt token in the response
        let token = response.data.value.token;
        if (token) {
          // set token property
          this.token = token;

          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));
        }
        return response;
      });
  }

  getUsername(): string {
    return JSON.parse(localStorage.getItem('currentUser')).username;
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    localStorage.removeItem('currentUser');
  }

  register(registrationRequest: RegistrationRequest): Observable<any> {
    return this.httpClient.post(AppSettings.API_ENDPOINT_AUTH_REGISTER, registrationRequest);
  }

  forgotpassword(email: string): Observable<any> {
    return this.httpClient.get(AppSettings.API_ENDPOINT_AUTH_REGISTER_FORGOTPASSWORD+"/"+email);
  }

  getUserDetails(): Observable<UserProfileRequest> {
    return this.httpClient.get<UserProfileRequest>(AppSettings.API_ENDPOINT_AUTH_SETTING_USERDETAILS);
  }

  updateUserDetails(userProfileRequest: UserProfileRequest): Observable<any> {
    return this.httpClient.post(AppSettings.API_ENDPOINT_AUTH_SETTING_UPDATEUSERDETAILS, userProfileRequest);
  }
}
