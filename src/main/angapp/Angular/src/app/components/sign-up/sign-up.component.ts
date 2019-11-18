import { Component, Inject, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
  providers: [HttpClient]
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

  genders = [
    {id: 1, name: "Male"},
    {id: 2, name: "Female"}
  ];
  model;

  constructor(@Inject(HttpClient) private http:HttpClient) {
    this.model = {
      bug: "No"
    };
  }

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
    body = body.set('login', "n@gmail.com");
    body = body.set('password', "qwerty");
    body = body.set('copyPassword', "qwerty");
    body = body.set('birthday', this.birthday);
    body = body.set('gender', "Male");
    body = body.set('bug', "No");
    body = body.set('comments', "No");
    this.http.post('http://localhost:8080/registrate',body).subscribe(value => {
      console.log(value);
    },error => {
      console.log(error);
    });
  }

}
