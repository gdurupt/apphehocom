import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Service } from '../interfaces/service';
import { User } from '../interfaces/user';
import { ServicesService } from '../services/services.service';
import { UsersService } from '../services/users.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.scss']
})
export class ServiceComponent implements OnInit {

  user: User;
  services: Service[];
  serviceForm: FormGroup
  serviceId;

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
      }
  });
  }

  updateService(){
    this.serviceService.UpdateService(this.serviceForm.value,this.serviceId).subscribe({
      next: data => {
        this.allServices();
        $('#update').modal('hide');
        this.clearForm();
      }
  });
  }

  deleteService(){
    this.serviceService.deleteService(this.serviceId).subscribe({
      next: data => {
        this.allServices();
      }
  });
  }

  addService(){
    this.serviceService.AddService(this.serviceForm.value).subscribe({
      next: data => {
        this.allServices();
        $('#service').modal('hide');
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


    this.serviceId = service.id;

    this.serviceForm.get('nom').setValue(service.nom);
    this.serviceForm.get('content').setValue(service.content);

    $('#update').modal('show');

    if(this.user.status != 'CLIENT'){
      for (let index = 0; index < this.services.length; index++) {
        this.services[index].BoolToChange = false;
      }
  
      this.services[id].BoolToChange = true;
    }

  }


  clearForm(){
    this.serviceForm.get('nom').setValue("");
    this.serviceForm.get('content').setValue("");
  }

  seeAll(){
    for (let index = 0; index < this.services.length; index++) {
      this.services[index].BoolToChange = false;
    }
  }
}
