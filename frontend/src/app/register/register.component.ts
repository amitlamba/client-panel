import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Response } from '@angular/http';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [ RegisterService ]
})
export class RegisterComponent implements OnInit {
  model: any = {};
  constructor(private registerService: RegisterService) {

  }
  ngOnInit() {
   this.registerService.getData()
    .subscribe(
      (data: Response) => console.log(data)
    );
  }
  
  onSubmit(form: NgForm) {
      console.log(form);
    }

}
