import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Site } from '../interfaces/site';

@Injectable({
  providedIn: 'root'
})
export class SitesService {

  site: Site[];

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }

/**
* 
* Recuperation de totu les sites
*/
getAllSite() : Observable<Site[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<Site[]>(environment.apiUrl + '/sites',{'headers':headers});
}

/**
* 
* Recuperation d'un site par son nom
*/
getOneSiteByName(name) : Observable<Site> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<Site>(environment.apiUrl + '/site/' + name,{'headers':headers});
}

/**
* 
* Recuperation d'un site par son id'
*/
getOneSiteById(id) : Observable<Site> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<Site>(environment.apiUrl + '/site/' + id,{'headers':headers});
}

/**
*  
*  Ajout d'un site
*/
addSite(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.post<any>(environment.apiUrl + '/site', body,{'headers':headers});
}

/**
*  
*  Ajout d'un commentaire avec un fichier
*/
AddComentAndFileBySite(file,id){
  
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('token')};

  let formDate = new FormData();

  formDate.append('file', file);

  return this.HttpClient.put<any>(environment.apiUrl + '/site/update/' + id + "/file", formDate,{'headers':headers} );
}

/**
*  
*  Modification Site
*/
updateSite(id,data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.put<any>(environment.apiUrl + '/site/update/' + id, body,{'headers':headers});
}

/**
*  
*  Modification hebergement Site
*/
updateHebergementSite(idSite,idHebergement){
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.put<any>(environment.apiUrl + '/site/update/' + idSite + '/' + idHebergement,{'headers':headers});
}

/**
*  
*  Suppression d'un site
*/
deleteSite(id){
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/site/' + id,{'headers':headers});
}

}
