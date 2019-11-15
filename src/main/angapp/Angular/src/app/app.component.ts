import {Component, OnInit} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular';

  login: string;
  password: string;
  http: HttpClient;
  buttontext: string = 'Sign up';
  refference: string = '/registration';

  constructor() { }

  changeButton() {
    if (location.href == 'http://localhost:4200/view') {
      this.buttontext = 'Log out';
    } else if (location.href == 'http://localhost:4200/') {
      this.buttontext = 'Sign up';
    }
    return this.buttontext;
  }

  changeReference() {
    if (location.href == 'http://localhost:4200/view') {
      this.refference = '/';
    } else if (location.href == 'http://localhost:4200/') {
      this.refference = '/registration';
    }
    return this.refference;
  }
}
