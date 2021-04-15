import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Service } from '../interfaces/service';
import { User } from '../interfaces/user';
import { ServicesService } from '../services/services.service';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.scss']
})
export class ServiceComponent implements OnInit {

  user: User;
  services: Service[];
  serviceForm: FormGroup

  constructor(
    private userService: UsersService,
    private serviceService: ServicesService,
    private formbuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getUser();
    this.allServices();
    this.initForm();
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

  initForm(){
    this.serviceForm = this.formbuilder.group({
      nom:['',Validators.required],
      content:['',Validators.required]
    });
  }


  allServices(){
    this.serviceService.getAllService().subscribe({
      next: data => {
        this.services = data;
        for (let index = 0; index < this.services.length; index++) {
          this.services[index].BoolToChange = false;
        }
      },
      error: error => {
      }
  });
  }

  updateService(service: Service){
    this.serviceService.UpdateService(this.serviceForm.value,service.id).subscribe({
      next: data => {
        this.allServices();
        console.log(this.serviceForm.value);
      },
      error: error => {
      }
  });
  }

  deleteService(service: Service){
    this.serviceService.deleteService(service.id).subscribe({
      next: data => {
        this.allServices();
      },
      error: error => {
      }
  });
  }

  addService(){
    this.serviceService.AddService(this.serviceForm.value).subscribe({
      next: data => {
        this.allServices();
      },
      error: error => {
      }
  });
  }

  checkUser(){
    if(this.user.status != 'CLIENT'){
      return 'cardPointer';
    }else{
      return '';
    }
  }

  changeService(service : Service,id){
    if(this.user.status != 'CLIENT'){
      for (let index = 0; index < this.services.length; index++) {
        this.services[index].BoolToChange = false;
      }
  
      this.services[id].BoolToChange = true;
    }
  }
}
