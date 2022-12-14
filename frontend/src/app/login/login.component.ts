import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {AuthenticationService} from "../_services/authentication.service";


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  public token: string;

  @Output() loginEvent = new EventEmitter();

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
    this.loading = true;

    const body = {username: this.model.username, password: this.model.password};
    this.authenticationService.login(this.model.username, this.model.password).subscribe(
      (response) => {
        console.log(response);
        this.loginEvent.emit();
        this.router.navigate(['/dashboard']);
      },
      (error: HttpErrorResponse) => {
        this.error = 'Username or password is incorrect';
        this.loading = false;
        console.log("this.error: " + this.error + ", this.loading: " + this.loading);
      }
    );
    /*this.http
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
      );*/
  }
}
