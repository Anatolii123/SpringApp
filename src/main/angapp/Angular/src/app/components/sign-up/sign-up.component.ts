import { Component, OnInit } from '@angular/core';
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  userName: string;
  userSurname: string;
  login: string;
  password: string;
  copyPassword: string;
  birthday: string;
  gender: string;
  bug: string;
  comments: string;
  salt: string;

  constructor() { }

  ngOnInit() {
    if (localStorage.getItem("login") != "" &&
      localStorage.getItem("login") != "null" &&
      localStorage.getItem("password") != "" &&
      localStorage.getItem("password") != "null") {
      location.href = 'http://localhost:4200/view';
    }
  }

  registrate() {
    let body = new HttpParams();
    body = body.set('name', this.userName);
    body = body.set('surname', this.userSurname);
    body = body.set('login', this.login);
    body = body.set('password', this.password);
    body = body.set('copyPassword', this.copyPassword);
    body = body.set('birthday', this.birthday);
    body = body.set('gender', this.gender);
    body = body.set('bug', this.bug);

  }

}
