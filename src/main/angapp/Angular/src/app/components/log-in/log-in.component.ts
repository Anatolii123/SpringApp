import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
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
    return this.http
      .get('http://localhost:8080/Salt')
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
    body = body.set('EMAIL', this.login);
    body = body.set('PASSWORD', e);
    body = body.set('SALT', this.salt);
    this.http.post('http://localhost:8080/View',body).subscribe(value => {
      console.log(value);
    });
  }

  submitForms() {
    this.getSalt();
  }
}
