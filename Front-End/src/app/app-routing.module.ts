import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddleaveComponent } from './addleave/addleave.component';
import { GetleaveComponent } from './getleave/getleave.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {'path' : 'login' , component:LoginComponent},
  {'path':'home', component:HomeComponent},
  {'path':'home/addleave',component:AddleaveComponent},
  {'path' : 'home/getLeaves' , component:GetleaveComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
