import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage/storage.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';


@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  constructor(private service:StorageService,private router: Router){
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.service.getToken();
    if(token!=null){
      authReq = authReq.clone({setHeaders:{Authorization:`Bearer ${token}`}});
    }
    if(this.service.getUser() == null){
      this.service.logout();
      this.router.navigate(['/']);
    }

    return next.handle(authReq);
  }

}

export const authInterceptor=[
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  }
];
