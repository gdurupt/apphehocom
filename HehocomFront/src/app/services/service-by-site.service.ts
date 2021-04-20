import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Service } from '../interfaces/service';

@Injectable({
  providedIn: 'root'
})
export class ServiceBySiteService {

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }

  /**
* 
* Recuperation des services lié a un site
*/
getServiceBySite(idSite) : Observable<Service[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Service[]>(environment.apiUrl + '/serviceBySite/' + idSite,{'headers':headers});
}


/**
*  
*  Ajout d'une nouvelle liaison
*/
AddServiceBySite(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.post<any>(environment.apiUrl + '/serviceBySite', body,{'headers':headers});
}


deleteIdService(id,idSite){
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')};
  return this.HttpClient.delete<any>(environment.apiUrl + '/serviceBySite/' + id + '/' + idSite,{'headers':headers});
}

}
