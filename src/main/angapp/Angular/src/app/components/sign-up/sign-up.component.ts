import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    if (localStorage.getItem("login") != "" && localStorage.getItem("password") != "") {
      location.href = 'http://localhost:4200/view';
    }
  }

}
