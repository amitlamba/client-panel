import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../_services/index';
import { HeaderComponent } from '../header/index';
import {Http, Response, RequestOptions, Headers} from '@angular/http';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';


@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html'
})
 
export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    error = '';
    public token: string;
    constructor(
        private http:HttpClient,
        private router: Router,
        private authenticationService: AuthenticationService) { 
            var currentUser = JSON.parse(localStorage.getItem('currentUser'));
            this.token = currentUser && currentUser.token;

        }
 
    ngOnInit() {
        
        this.authenticationService.logout();
    }
 
    login() {

        const body = {username: this.model.username,password:this.model.password};
        this.http
        .post('/auth', body)
        .map((response: Response) =>{
            console.log(response.type);
            let json = JSON.stringify(response);
            let finalJson = JSON.parse(json);
            return finalJson;
            })
        .subscribe(
            (response: JSON) => {
                let token = response['token'];
                                if (token) {
                                    this.token = token;
                                    localStorage.setItem('currentUser', JSON.stringify({ username: this.model.username, token: token }));
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