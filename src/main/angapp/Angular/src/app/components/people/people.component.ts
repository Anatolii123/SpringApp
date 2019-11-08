import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  id: number;
  name: string;
  surname: string;
  email: string;
  password: string;
  dateOfBirth: string;
  gender: string;
  bug: string;
  comments: string;

  constructor() { }

  ngOnInit() {
  }

}
