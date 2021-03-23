import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorLogin = false;

  constructor(
    private formbuilder: FormBuilder, 
    private userService: UsersService, 
    private router: Router
  ) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('tokenhehocom')){
      this.router.navigateByUrl('/dashboard');
    }
    this.initLoginForm();
    sessionStorage.removeItem('tokenhehocom');
  }

  initLoginForm(){
    this.loginForm = this.formbuilder.group({
      email: ['',Validators.required],
      password: ['',Validators.required]
    })
  }

  onSubmitLogin(){
    this.userService.login(this.loginForm.value).subscribe({
      next: data => {
        sessionStorage.setItem('tokenhehocom',data['token']);
        this.router.navigateByUrl('/dashboard');
      },
      error: error => {
        this.errorLogin = true;
      }
  });
  }

}
