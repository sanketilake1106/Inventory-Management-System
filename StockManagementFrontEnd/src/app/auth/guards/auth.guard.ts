import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { StorageService } from '../services/storage/storage.service';
import { Inject, Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private service: StorageService,
    private router: Router) { }

  canActivate(){
    if (this.service.getToken() !== null &&
    this.service.getUser() !== null &&
    this.service.getUserRole() == "ADMIN") {
      return true;
    }
    // this.router.navigate(['/']);
    return false;
  }

}
