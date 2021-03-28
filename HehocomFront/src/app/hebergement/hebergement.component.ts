import { Component, OnInit } from '@angular/core';
import { User } from '../interfaces/user';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-hebergement',
  templateUrl: './hebergement.component.html',
  styleUrls: ['./hebergement.component.scss']
})
export class HebergementComponent implements OnInit {

  user: User;

  constructor(
    private userService: UsersService
  ) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(){
    this.userService.getTokenUser().subscribe({
      next: data => {
        this.user = data;
      },
      error: error => {
      }
  });
  }
}
