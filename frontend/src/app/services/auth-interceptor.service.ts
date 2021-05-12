import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';

import { AppCookieService } from './app-cookie.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private appCookieService: AppCookieService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let token = this.appCookieService.getAccessToken();
    console.log(`token`, token);

    if (token) {
      // modify header
      let modified = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`),
      });
      console.log(`modified`, modified);

      return next.handle(modified);
    }

    return next.handle(req);
  }
}
