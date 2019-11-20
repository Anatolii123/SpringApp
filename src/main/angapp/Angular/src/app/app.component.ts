import {Component, Inject} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpClient]
})
export class AppComponent {
  title = 'Angular';

  login: string;
  password: string;
  buttontext: string = 'Sign up';
  refference: string = '/registration';
  location: string;

  constructor(@Inject(HttpClient) private http:HttpClient) {
  }

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
    } else if (location.href == 'http://localhost:4200/' || (location.href == 'http://localhost:4200/about' )) {
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
      let body = new HttpParams();
      localStorage.setItem("login", "null");
      localStorage.setItem("password", "null");
      body = body.set('login', localStorage.getItem("login"));
      this.http.post('http://localhost:8080/logout',body).subscribe(value => {
      });
    }
  }

}
