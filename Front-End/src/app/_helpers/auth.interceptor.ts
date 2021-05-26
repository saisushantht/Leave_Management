import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';
 
 
const HEADER_KEY = 'Authorization'; 
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
 
  constructor( private token : TokenStorageService) {}
 
  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let authReq = req;
    const token = this.token.getToken();
    if(token != null){
      authReq = req.clone({ headers : req.headers.set(HEADER_KEY,'Bearer '+token)})
    }
    return next.handle(authReq);
  }
  
}
 
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
 

