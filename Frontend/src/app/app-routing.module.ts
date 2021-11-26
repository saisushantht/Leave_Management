import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddleaveComponent } from './addleave/addleave.component';
import { GetleaveComponent } from './getleave/getleave.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ManagerComponent } from './manager/manager.component';
import { AuthGuardService } from './_services/auth-guard.service';
import { RoleGuardService } from './_services/role-guard.service';

const routes: Routes = [
  {'path' : '' , component:LoginComponent},
  {'path':'home', component:HomeComponent,canActivate:[RoleGuardService,AuthGuardService],data:{expectedRole:['EMPLOYEE']}},
  {'path':'home/addleave',component:AddleaveComponent,canActivate:[RoleGuardService,AuthGuardService],data:{expectedRole:['EMPLOYEE']}},
  {'path' : 'home/getLeaves' , component:GetleaveComponent,canActivate:[RoleGuardService,AuthGuardService],data:{expectedRole:['EMPLOYEE']}},
  {'path':'manager', component:ManagerComponent,canActivate:[RoleGuardService,AuthGuardService],data:{expectedRole:['MANAGER']}},
  
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
