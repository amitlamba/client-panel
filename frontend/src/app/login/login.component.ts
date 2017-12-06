import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {AuthenticationService} from "../_services/authentication.service";
import {AppSettings} from "../_settings/app-settings";


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  public token: string;

  constructor(private http: HttpClient,
              private router: Router,
              private authenticationService: AuthenticationService) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;

  }

  ngOnInit() {

    this.authenticationService.logout();
  }

  login() {

    const body = {username: this.model.username, password: this.model.password};
    this.http
      .post(AppSettings.API_ENDPOINT_AUTH+'/auth', body)
      .subscribe(
        (response: any) => {
          let token = response.data.value.token;
          if (token) {
            this.token = token;
            localStorage.setItem('currentUser', JSON.stringify({username: this.model.username, token: token}));
            this.router.navigate(['/']);
          } else {
            this.error = 'Username or password is incorrect';
            this.loading = false;
            return false;
          }
        }
      );
  }
}
