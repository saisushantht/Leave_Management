import { Route } from '@angular/compiler/src/core';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate{
  b=false;
  constructor(public auth: AuthService, public router: Router , public tokenStorage: TokenStorageService,) {}
  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    const token = this.tokenStorage.getToken();
    console.log(this.tokenStorage.getUser().roles[0])
    for(var val of expectedRole){
      if(this.tokenStorage.getUser().roles[0]===val){
        this.b=true;
      }
    }

    // decode the token to get its payload
    if (
      !this.auth.isLoggedIn || !this.b

    ) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
