import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular';

  http: HttpClient;
  buttontext: string = 'Sign up';
  refference: string = '/registration';
  location: string;

  userName: string;
  userSurname: string;
  login: string;
  password: string;
  copyPassword: string;
  birthday: string;
  gender: string;
  bug: string;
  comments: string;

  constructor() { }

  changeButton() {
    if (location.href == 'http://localhost:4200/view') {
      this.buttontext = 'Log out';
    } else if (location.href == 'http://localhost:4200/') {
      this.buttontext = 'Sign up';
    }
    return this.buttontext;
  }

  thisReference() {
    if (localStorage.getItem("login") != "" &&
      localStorage.getItem("login") != "null" &&
      localStorage.getItem("password") != "" &&
      localStorage.getItem("password") != "null") {
      this.refference = '/view';
    } else {
      this.refference = '/';
    }
    return this.refference;
  }

  changeReference() {
    if (location.href == 'http://localhost:4200/view') {
      this.location = 'http://localhost:4200/view';
      this.refference = '/';
    } else if (location.href == 'http://localhost:4200/' || location.href == 'http://localhost:4200/about') {
      this.location = 'http://localhost:4200/';
      this.refference = '/registration';
    } else if (location.href == 'http://localhost:4200/registration') {
      this.location = 'http://localhost:4200/registration';
      this.refference = '/registration';
    }
    return this.refference;
  }

  changeFunction() {
    if (location.href == 'http://localhost:4200/view') {
      localStorage.setItem("login", "null");
      localStorage.setItem("password", "null");
    }
  }

}
