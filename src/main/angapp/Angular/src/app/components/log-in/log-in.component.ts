import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpRequest} from "@angular/common/http";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
  providers: [HttpClient]
})
export class LogInComponent implements OnInit {

  login:string;
  password:string;
  salt: string;
  http: HttpClient;

  constructor() { }

  ngOnInit() {
  }

  postData() {
    const body = {EMAIL: this.login, PASSWORD: this.password};
    return this.http.post('http://localhost:8080/View', body);
  }


}
