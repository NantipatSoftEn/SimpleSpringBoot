import { HttpClient } from '@angular/common/http';
import { ILoginResponse } from '../interfaces/i-login-response';
import { IRegisterResponse } from '../interfaces/i-register-response';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const url = 'http://localhost:8080/user';
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
    return this.http.post<ILoginResponse>(`${url}/login`, body);
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
    return this.http.post<IRegisterResponse>(`${url}/register`, body);
  }

  activateAccount(token: string): Observable<any> {
    let body = {
      token: token,
    };
    return this.http.post<any>(`${url}/activate`, body);
  }

  resendActivationEmail(token: string) {
    let body = {
      token: token,
    };
    return this.http.post<any>(`${url}/resend-activation-email`, body);
  }
}
