import { Component, Inject, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import * as bigInt from "big-integer";
import {Md5} from "md5-typescript";
declare function strToBI(): any;

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

  genders = [
    {id: 1, name: "Male"},
    {id: 2, name: "Female"}
  ];
  model;

  publicKey;
  privateKey;
  publicValue;
  resultKey;
  e;

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

  diffieHellman(num, exp) {
    var key = bigInt(num).pow(bigInt(exp)).mod(bigInt(983));
    return key;
  }

  getPublicValue() {
    let body = new HttpParams();
    this.privateKey = bigInt(parseInt((Math.random() * 1000).toString(), 10));
    this.publicKey = this.diffieHellman(1000,this.privateKey);
    body = body.set('publicValue', this.publicKey.toString());
    body = body.set('login', this.login);
    this.http.post('http://localhost:8080/key',body).subscribe(value => {
      this.publicValue = value['publicValue'];
      this.resultKey = this.diffieHellman(this.publicValue,this.privateKey);
      strToBI.prototype.testFunction();
      this.e = (bigInt(Md5.init(this.password)).xor(bigInt(this.resultKey))).toString(16);
      this.registrate();
    })
  }

  registrate() {
    let body = new HttpParams();
    body = body.set('name', this.userName);
    body = body.set('surname', this.userSurname);
    body = body.set('login', this.login);
    body = body.set('password', this.e);
    body = body.set('copyPassword', this.copyPassword);
    body = body.set('birthday', this.birthday);
    body = body.set('gender', this.gender);
    body = body.set('bug', this.model.bug);
    body = body.set('comments', this.comments);
    this.http.post('http://localhost:8080/registrate',body).subscribe(value => {
      console.log(value);
      if (value) {
        localStorage.setItem("login",this.login);
        localStorage.setItem("password",this.password);
        location.href = 'http://localhost:4200/view';
        return;
      }
    });
  }
}
