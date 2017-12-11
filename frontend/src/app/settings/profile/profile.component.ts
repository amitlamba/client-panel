import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from "../../_services/authentication.service";
import {UserProfileRequest} from "../../_models/client";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @ViewChild('f') form: any;

  userProfile: UserProfileRequest;
  successMessage: string = "";
  username: string;

  langs: string[] = [
    'English',
    'French',
    'German',
  ];


  onSubmit(form: FormData) {
    if (this.form.valid) {
      this.authenticationService.updateUserDetails(this.userProfile)
        .subscribe(
          response => {
            this.successMessage = "User Profile Update Successfullly";
          }
        );
    }
  }


  constructor(private httpClient: HttpClient, private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.authenticationService.getUserDetails().subscribe(
      (userProfile: UserProfileRequest) => {
        this.userProfile = userProfile;
      }
    )
    this.username = this.authenticationService.getUsername();
  }

}
