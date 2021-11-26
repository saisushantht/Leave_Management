import { Component } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'leaveManagement';
  isLoggedin =false;
  constructor(private tokenStrorageService:TokenStorageService
    ){}
  ngOnInit ():void{
    this.isLoggedin = !!this.tokenStrorageService.getToken()
    
  }
  logout(): void{
    window.sessionStorage.clear();
    window.location.href = ''
  }
}
