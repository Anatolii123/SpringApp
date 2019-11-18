import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Md5} from "md5-typescript";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
  providers: [HttpClient]
})
export class LogInComponent implements OnInit {

  login: string;
  password: string;
  salt: string;

  constructor(@Inject(HttpClient) private http:HttpClient) {
  }

  ngOnInit() {
    if (localStorage.getItem("login") != "null" && localStorage.getItem("password") !=" null") {
      this.login = localStorage.getItem("login");
      this.password = localStorage.getItem("password");
      return;
    }
    this.login = "";
    this.password = "";
  }

  getSalt() {
    let body = new HttpParams();
    body = body.set('login', this.login);
    return this.http
      .post('http://localhost:8080/Salt',body)
      .subscribe(value => {
        this.salt = value['salt'];
        this.postData();
      },error => {
        this.salt = error.toString();
      });
  }

  postData() {
    let e = Md5.init(this.login + Md5.init(this.password) + this.salt);
    let body = new HttpParams();
    body = body.set('login', this.login);
    body = body.set('password', e);
    body = body.set('salt', this.salt);
    this.http.post('http://localhost:8080/login',body).subscribe(value => {
      console.log(value);
      if (value) {
        localStorage.setItem("login",this.login);
        localStorage.setItem("password",this.password);
        location.href = 'http://localhost:4200/view';
        return;
      }
      location.href = 'http://localhost:4200/';
    });
  }

  submitForms() {
    this.getSalt();
  }

}
