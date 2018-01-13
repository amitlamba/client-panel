import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-reset-pwd',
  templateUrl: './reset-pwd.component.html',
  styleUrls: ['./reset-pwd.component.css']
})
export class ResetPwdComponent implements OnInit {

  code: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.code = this.route.snapshot.params["code"];
  }

}
