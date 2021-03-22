import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Service } from '../interfaces/service';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }

    /**
* 
* Recuperation des services
*/
getAllService(id) : Observable<Service[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<Service[]>(environment.apiUrl + '/service/' + id,{'headers':headers});
}


/**
*  
*  Ajout d'un service
*/
AddService(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.post<any>(environment.apiUrl + '/service', body,{'headers':headers});
}

/**
*  
*  Modification d'un service
*/
UpdateService(data,id){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.put<any>(environment.apiUrl + '/service/' + id, body,{'headers':headers});
}

/**
* 
* Suppression d'un service
*/
deleteService(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/service/' + id,{'headers':headers});
}
}
