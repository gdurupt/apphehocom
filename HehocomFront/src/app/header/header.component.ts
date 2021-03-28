import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../interfaces/user';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: User;

  constructor(
    private router: Router,
    private userService: UsersService
  ) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('tokenhehocom')){
      this.userService.getTokenUser().subscribe({
        next: data => {
          this.user = data;
        },
        error: error => {
          this.router.navigateByUrl('');
        }
    });
    }else{
      this.router.navigateByUrl('');
    }
  }


  deconnexion(){
    sessionStorage.removeItem("tokenhehocom");
    this.router.navigateByUrl('');
  }

}
