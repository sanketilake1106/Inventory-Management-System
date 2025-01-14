import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import BASE_URL from './helper';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(user: any): Observable<any> {
    return this.http.post(BASE_URL + "/api/service/login", user);
  }

  public registerUser(user: any): Observable<any> {
    return this.http.post(BASE_URL + "/user/addUser", user);
  }

  public getCurrentUser(): Observable<any>{
    return this.http.get(BASE_URL + "/api/service/current-user");
  }
}
