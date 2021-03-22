import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Hebergement } from '../interfaces/hebergement';

@Injectable({
  providedIn: 'root'
})
export class HebergementsService {

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }

    /**
* 
* Recuperation des hebergements
*/
getHebergementBySite(id) : Observable<Hebergement[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<Hebergement[]>(environment.apiUrl + '/hebergement/' + id,{'headers':headers});
}


/**
*  
*  Ajout d'un hebergement
*/
AddHebergementBySite(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.post<any>(environment.apiUrl + '/hebergement', body,{'headers':headers});
}

/**
*  
*  Modification d'un hebergement
*/
UpdateHebergementBySite(data,id){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.put<any>(environment.apiUrl + '/hebergement/' + id, body,{'headers':headers});
}

/**
* 
* Suppression d'un hebergement
*/
deleteHebergementBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/hebergement/' + id,{'headers':headers});
}
}
