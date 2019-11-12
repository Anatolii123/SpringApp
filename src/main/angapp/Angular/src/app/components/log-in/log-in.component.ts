import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient, HttpRequest} from "@angular/common/http";
import {Md5} from "md5-typescript";
import {Observable, pipe} from 'rxjs';
import { map } from 'rxjs/operators';

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
  }

  getSalt() {
    const body = null;
    return this.http
      .get('http://localhost:8080/Salt')
      .subscribe(value => {
        this.salt = value.toString();
        this.postData();
      },error => {
        this.salt = error.toString();
      });
  }

  postData() {
    let e = Md5.init(this.login + Md5.init(this.password) + this.salt);
    const body = {EMAIL: this.login, PASSWORD: this.salt};
    return this.http.post('http://localhost:8080/View', body);
  }

  submitForms() {
    this.getSalt();
    /*var form1 = <HTMLFormElement>document.getElementById('form1');
    var form2 = <HTMLFormElement>document.getElementById('form2');
    const promise = new Promise((resolve, reject) => {
      form2.submit();
    });
    promise.then(function (result) {
      form1.submit();
    });*/
  }
}
