import { HttpClient } from '@angular/common/http';
import { identifierModuleUrl } from '@angular/compiler';
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
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Site[]>(environment.apiUrl + '/userbysite/byUser',{'headers':headers});
}


/**
*  
*  Ajout d'une nouvelle liaison
*/
addUserBySite(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.post<any>(environment.apiUrl + '/userbysite', body,{'headers':headers});
}

/**
*  
*  Ajout d'une nouvelle liaison
*/
adUserBySite(name,id){
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<any>(environment.apiUrl + '/userbysite/' + name + '/' + id,{'headers':headers});
}

/**
* 
* Recuperation des sites lié a un utilisateur
*/
getAllSiteByUser(id) : Observable<Site[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Site[]>(environment.apiUrl + '/userbysite/byUser/' + id,{'headers':headers});
}

/**
* 
* Recuperation des sites lié a un utilisateur
*/
removeUserBySite(idSite,idUser){
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/userbysite/byUser/' + idUser + '/' + idSite,{'headers':headers});
}

}
