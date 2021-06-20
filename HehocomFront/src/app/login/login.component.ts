import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmailService } from '../services/email.service';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  lostPasswordForm: FormGroup;
  errorLogin = false;
  lostpassword = false;
  errorLostPassword = false;

  constructor(
    private formbuilder: FormBuilder, 
    private userService: UsersService, 
    private router: Router,
    private emailService : EmailService
  ) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('tokenhehocom')){
      this.router.navigateByUrl('/dashboard');
    }
    this.initLoginForm();
    this.initLostPasswordForm();
    this.lostpassword = false;
  }

  initLoginForm(){
    this.loginForm = this.formbuilder.group({
      email: ['',Validators.required],
      password: ['',Validators.required]
    })
  }

  initLostPasswordForm(){
    this.lostPasswordForm = this.formbuilder.group({
      email: ['',Validators.required],
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

  clickLostPassword(){
    this.lostpassword = true;
  }

  lostPassword(){
    this.emailService.lostPassword(this.lostPasswordForm.value).subscribe({
      next: data => {
        this.lostpassword = true;
        this.errorLostPassword = false;
      },
      error: error => {
        this.errorLostPassword = true;
      }
  });
  }

}
