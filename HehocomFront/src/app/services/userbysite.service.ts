import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Site } from '../interfaces/site';
import { UserBySite } from '../interfaces/user-by-site';

@Injectable({
  providedIn: 'root'
})
export class UserbysiteService {

  userBySite: UserBySite[];

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }


/**
* 
* Recuperation des sites lié a un utilisateur
*/
getOneUser() : Observable<Site[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<Site[]>(environment.apiUrl + '/userbysite',{'headers':headers});
}


/**
*  
*  Ajout d'une nouvelle liaison
*/
login(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.post<any>(environment.apiUrl + '/userbysite', body,{'headers':headers});
}

}
