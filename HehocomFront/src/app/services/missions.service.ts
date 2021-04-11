import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Mission } from '../interfaces/mission';

@Injectable({
  providedIn: 'root'
})
export class MissionsService {

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }



  /**
* 
* Recuperation des commentaires lié a un site
*/
getMissionBySite(idSite,statut,offset) : Observable<Mission[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Mission[]>(environment.apiUrl + '/mission/' + idSite + '/' + statut + '/' + offset,{'headers':headers});
}

  /**
* 
* Recuperation des mission requete client
*/
getallMissionRequeteClientBySite(idSite) : Observable<Mission[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Mission[]>(environment.apiUrl + '/mission/requeteclient/' + idSite,{'headers':headers});
}

  /**
* 
* Recuperation des commentaires lié a un site
*/
getCountMissionBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<number>(environment.apiUrl + '/mission/count/' + id,{'headers':headers});
}

/**
* 
* Recuperation des commentaires lié a un site
*/
getLastMissionBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Mission>(environment.apiUrl + '/mission/last/' + id,{'headers':headers});
}


/**
*  
*  Ajout d'une mission
*/
AddMissionBySite(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.post<any>(environment.apiUrl + '/mission', body,{'headers':headers});
}

/**
*  
*  Modification d'une mission
*/
UpdateMissionBySite(data,id){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.put<any>(environment.apiUrl + '/mission/' + id, body,{'headers':headers});
}

/**
* 
* Suppression d'une mission
*/
deleteMissionBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/mission/' + id,{'headers':headers});
}
}
