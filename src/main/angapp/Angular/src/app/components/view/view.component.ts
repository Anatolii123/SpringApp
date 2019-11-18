import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  login: string;

  constructor() { }

  ngOnInit() {
    this.login = localStorage.getItem("login");
    if (localStorage.getItem("login") == "null" ||
      localStorage.getItem("login") == "") {
      location.href = 'http://localhost:4200/';
    }
  }

}
