import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ILoginResponse } from '../interfaces/i-login-response';
import { IRegisterResponse } from '../interfaces/i-register-response';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = 'http://localhost:8080/user';
@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<ILoginResponse> {
    let body = {
      email: email,
      password: password,
    };
    return this.http.post<ILoginResponse>(`${URL}/login`, body);
  }

  register(
    email: string,
    password: string,
    name: string
  ): Observable<IRegisterResponse> {
    let body = {
      email: email,
      password: password,
      name: name,
    };
    return this.http.post<IRegisterResponse>(`${URL}/register`, body);
  }

  activateAccount(token: string): Observable<any> {
    let body = {
      token: token,
    };
    return this.http.post<any>(`${URL}/activate`, body);
  }

  resendActivationEmail(token: string) {
    let body = {
      token: token,
    };
    return this.http.post<any>(`${URL}/resend-activation-email`, body);
  }

  getAllUsers(token: string) {
    let headers = new HttpHeaders();
    headers = headers
      .set('Content-Type', 'application/json; charset=utf-8')
      .set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${URL}/resend-activation-email`, {
      headers: headers,
    });
  }
}
