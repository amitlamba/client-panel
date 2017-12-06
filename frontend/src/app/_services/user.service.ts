import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'

import {User} from "../_models/user";
import {AuthenticationService} from "./authentication.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class UserService {
  constructor(private httpClient: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  getUsers(): Observable<User[]> {

    let headers = new HttpHeaders({ 'authorization': this.authenticationService.token });
    let options = { headers: headers };

    // get users from api
    // return this.httpClient.get<User[]>(AppSettings.API_ENDPOINT_CLIENT_CLIENT_USERS_GETLIST);
    return this.httpClient.get<User[]>("http://localhost:9191/client/users/get-list",
      options);
  }
}
