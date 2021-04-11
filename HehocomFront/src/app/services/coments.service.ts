import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Coment } from '../interfaces/coment';

@Injectable({
  providedIn: 'root'
})
export class ComentsService {

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) { }

/**
* 
* Recuperation des commentaires lié a un site
*/
getComentBySite(id,offset) : Observable<Coment[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Coment[]>(environment.apiUrl + '/coment/' + id + '/' + offset,{'headers':headers});
}

  /**
* 
* Recuperation des commentaires lié a un site
*/
getCountComentBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<number>(environment.apiUrl + '/coment/count/' + id,{'headers':headers});
}

/**
* 
* Recuperation des commentaires lié a un site
*/
getLastComentBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.get<Coment>(environment.apiUrl + '/coment/last/' + id,{'headers':headers});
}


/**
*  
*  Ajout d'un commentaire
*/
AddComentBySite(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.post<any>(environment.apiUrl + '/coment/text', body,{'headers':headers});
}

/**
*  
*  Ajout d'un commentaire avec un fichier
*/
AddComentAndFileBySite(data,file){
  
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')};

  let formDate = new FormData();

  formDate.append('file', file);

  const json = JSON.stringify(data);

  const blob = new Blob([json], {
    type: "application/json",
  });

  formDate.append('coment',blob);

  return this.HttpClient.post<any>(environment.apiUrl + '/coment/file', formDate,{'headers':headers} );
}

/**
*  
*  Modification d'un commentaire
*/
UpdateComentBySite(data,id){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.put<any>(environment.apiUrl + '/coment/' + id, body,{'headers':headers});
}

/**
* 
* Suppression d'un commentaire
*/
deleteComentBySite(id) {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('tokenhehocom')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/coment/' + id,{'headers':headers});
}



}
