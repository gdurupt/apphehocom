import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../interfaces/user';
import { UsersService } from '../services/users.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-gestion-user',
  templateUrl: './gestion-user.component.html',
  styleUrls: ['./gestion-user.component.scss']
})
export class GestionUserComponent implements OnInit {

  userCo: User;
  users: User[];

  userForm: FormGroup;

  deleteId = 0;
  nameDelete;

  idUpdate;
  nameupdate;

  searchform: FormGroup;

  userSearch : User;

  errorForm = false;

  constructor(
    private userService: UsersService,
    private formbuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getUser();
    this.getAllUser();
    this.initUserForm();
  }

  getUser(){
    this.userService.getTokenUser().subscribe({
      next: data => {
        this.userCo = data;
      },
      error: error => {
      }
  });
  }

  getAllUser(){
    this.userService.getAllUsers().subscribe({
      next: data => {
        this.users = data;
      },
      error: error => {
      }
  });
  }



  clearForm(){
    this.userForm.get('email').setValue('');
    this.userForm.get('secondName').setValue('');
    this.userForm.get('tel').setValue('');
    this.userForm.get('username').setValue('');
    this.userForm.get('cpassword').setValue('');
    this.userForm.get('password').setValue('');
    this.userForm.get('status').setValue('');  
  }

  searchUserName(){
    this.userService.getOneUserByMail(this.searchform.get('email').value).subscribe({
      next: data => {
        this.userSearch = data;
        this.errorForm = false;
        if(data == null){
          this.errorForm = true;
        }
      },
      error: error => {
      }
  });
  }

  deleteModal(user : User){
    this.deleteId = user.id;
    this.nameDelete = user.username + ' ' + user.secondname;
  }

  deleteUser(){
    this.userService.deleteUser(this.deleteId).subscribe({
      next: data => {
        $('#supprimerUser').modal('hide');
        this.getAllUser();
        this.deleteId = 0;
        this.nameDelete = '';
        if(this.searchform.get('email').value != null){
          this.searchUserName()
        }
      },
      error: error => {
      }
  });
  }

  initUserForm(){
    this.searchform = this.formbuilder.group({
      email: ['',Validators.required],
    })

    this.userForm = this.formbuilder.group({
      email: ['',Validators.required],
      secondName: ['',Validators.required],
      tel: ['',Validators.required],
      username: ['',Validators.required],
      cpassword: [''],
      password: [''],
      status: ['']
    })
  }


  updateUser(){
    this.userService.UpdateProfile(this.idUpdate,this.userForm.value).subscribe({
      next: data => {
        this.getAllUser();
        if(this.searchform.get('email').value != ''){
          this.searchUserName()
        }
      },
      error: error => {
      }
  });
  this.userService.changeStatus(this.idUpdate,this.userForm.value).subscribe({
    next: data => {
      this.getAllUser();
      if(this.searchform.get('email').value != ''){
        this.searchUserName()
      }
    },
    error: error => {
    }
  });


if(this.userForm.get('password').value  == this.userForm.get('cpassword').value && this.userForm.get('cpassword').value != '' && this.userForm.get('password').value != ''){
  this.userService.changePassword(this.idUpdate,this.userForm.value).subscribe({
    next: data => {
      this.getAllUser();
      if(this.searchform.get('email').value != ''){
        this.searchUserName()
      }
    },
    error: error => {
    }
});
}
$('#modifierUser').modal('hide');

  }

  updateModal(user : User){
    this.idUpdate = user.id;
    this.nameupdate = user.username;

    this.userForm.get('email').setValue(user.email);
    this.userForm.get('secondName').setValue(user.secondname);
    this.userForm.get('tel').setValue(user.tel);
    this.userForm.get('username').setValue(user.username);
    this.userForm.get('cpassword').setValue('');
    this.userForm.get('password').setValue('');
    this.userForm.get('status').setValue(user.status);  
  }

  addUser(){
    this.userService.register(this.userForm.value).subscribe({
      next: data => {
        $('#ajouteruser').modal('hide');
        this.getAllUser();

      },
      error: error => {
      }
  });
  }
}
