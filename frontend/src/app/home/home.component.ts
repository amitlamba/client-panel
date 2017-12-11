import {Component, OnInit} from '@angular/core';

import {EventUser, User} from "../_models/user";
import {UserService} from "../_services/user.service";

@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
  title: string = "Dashboard"
  users: EventUser[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    // get users from secure api end point
    this.userService.getUsers()
      .subscribe(users => {
        this.users = users;
      });
  }

}
