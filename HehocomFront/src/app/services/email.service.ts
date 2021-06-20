import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(
    private HttpClient: HttpClient,
  ) { }

  /**
  *  
  *  nvoie email lost Password
  */
  lostPassword(data){
    const body=JSON.stringify(data);
    const headers = { 'content-type': 'application/json'}
    return this.HttpClient.post<any>(environment.apiUrl + '/email/lostpassword', body,{'headers':headers});
  }

}
