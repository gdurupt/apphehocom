import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {


  users: User[];

  constructor(
    private HttpClient: HttpClient,
    private Router: Router
  ) {}


/**
* 
* Recuperation de l'utilisateur lier a son token
*/
  getOneUser() : Observable<User> {
    const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
    return this.HttpClient.get<User>(environment.apiUrl + '/member',{'headers':headers});
  }

/**
* 
* Recuperation de tout les utilisateur
*/
  getAllUsers() : Observable<User[]> {
    const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
    return this.HttpClient.get<User[]>(environment.apiUrl + '/profile',{'headers':headers});
  }

/**
* 
* Recuperation d'un utilisateur par Id
*/
getOneUserById(id) : Observable<User[]> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<User[]>(environment.apiUrl + '/profile/' + id,{'headers':headers});
}

/**
* 
* Recuperation d'un utilisateur par mail
*/
getOneUserByMail(mail) : Observable<User> {
  const headers = { 'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.get<User>(environment.apiUrl + '/profile/search/' + mail,{'headers':headers});
}

/**
*  
*  Connexion d'un utilisateur
*/
register(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json'}
  return this.HttpClient.post<any>(environment.apiUrl + '/register', body,{'headers':headers});
}

/**
*  
*  Connexion d'un utilisateur
*/
login(data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json'}
  return this.HttpClient.post<any>(environment.apiUrl + '/login', body,{'headers':headers});
}

/**
*  
*  Changement de status
*/
changeStatus(id,data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.put<any>(environment.apiUrl + '/admin/members/' + id + '/status', body,{'headers':headers});
}


/**
*  
*  Modification de profile
*/
UpdateProfile(id,data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.put<any>(environment.apiUrl + '/profile/' + id, body,{'headers':headers});
}

/**
*  
*  Changement de password
*/
changePassword(id,data){
  const body=JSON.stringify(data);
  const headers = { 'content-type': 'application/json','Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.post<any>(environment.apiUrl + '/profile/password/' + id, body,{'headers':headers});
}

/**
*  
*  Suppression d'un utilisateur
*/
deleteUser(id){
  const headers = {'Authorization': 'Bearer ' + sessionStorage.getItem('token')}
  return this.HttpClient.delete<any>(environment.apiUrl + '/profile/' + id,{'headers':headers});
}

}
