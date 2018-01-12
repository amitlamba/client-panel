import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'
import {HttpClient} from '@angular/common/http';
import {AppSettings} from "../_settings/app-settings";
import {RegistrationRequest, ServiceProviderCredentials, UserProfileRequest} from "../_models/client";
import {MessageService} from "./message.service";
import {of} from "rxjs/observable/of";
import {catchError, tap} from "rxjs/operators";

@Injectable()
export class AuthenticationService {
  public token: string;

  constructor(private httpClient: HttpClient, private messageService: MessageService) {
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
    return this.httpClient.post(AppSettings.API_ENDPOINT_AUTH_REGISTER, registrationRequest)
      .pipe(
        tap(next => this.messageService.addSuccessMessage(registrationRequest.name + " registered successfuly.")),
        catchError(this.handleError<any>('register'))
      );
  }

  forgotpassword(email: string): Observable<any> {
    return this.httpClient.get(AppSettings.API_ENDPOINT_AUTH_REGISTER_FORGOTPASSWORD + "/" + email)
      .pipe(
        tap(next => this.messageService.addSuccessMessage("Please see your email " +email + " to reset password.")),
        catchError(this.handleError<any>('forgotpassword'))
      );
  }

  getUserDetails(): Observable<any> {
    return this.httpClient.get<any>(AppSettings.API_ENDPOINT_AUTH_SETTING_USERDETAILS)
      .pipe(
        tap(next => this.messageService.addSuccessMessage("User Details Fetched successfully")),
        catchError(this.handleError<any>('User Details fetching'))
      );
  }

  updateUserDetails(userProfileRequest: UserProfileRequest): Observable<any> {
    return this.httpClient.post(AppSettings.API_ENDPOINT_AUTH_SETTING_UPDATEUSERDETAILS, userProfileRequest)
      .pipe(
        tap(next => this.messageService.addSuccessMessage("User Details updated successfully")),
        catchError(this.handleError<any>('User Details Update'))
      );
  }

  getServiceProviderCredentialsEmail(): Observable<ServiceProviderCredentials[]> {
    return this.httpClient.get(AppSettings.API_ENDPOINT_CLIENT_SETTING_EMAIL_SERVICE_PROVIDERS)
      .pipe(
        tap(next => this.messageService.addSuccessMessage("Got Service Provider Credentials For Email successfully")),
        catchError(this.handleError<any>('Get Service Provider Credentials For Email'))
      );
  }

  saveServiceProviderCredentialEmail(serviceProviderCredentials: ServiceProviderCredentials): Observable<any> {
    return this.httpClient.post(AppSettings.API_ENDPOINT_CLIENT_SETTING_EMAIL_SERVICE_PROVIDER_SAVE, serviceProviderCredentials)
      .pipe(
        tap(next => this.messageService.addSuccessMessage("Saved Service Provider Credentials For Email successfully")),
        catchError(this.handleError<any>('Save Service Provider Credentials For Email'))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.messageService.addDangerMessage(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
