import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Details } from '../models/details.model';
import { DetailsService } from '../_services/details.service';
import { LeaveService } from '../_services/leave.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  detail?:Details[];
  username='';
  count=''
  

  constructor(private detailsService:DetailsService , private route:ActivatedRoute , private router:Router) { }

  onSubmit(): void{
    window.sessionStorage.clear();
    window.location.href = 'login'
  }
  ngOnInit(): void {

  }

  retriveDetails(username:string): void{
     this.detailsService.getUserName(username).subscribe(
      data =>{this.detail=data;
        console.log(data)}

     )
  }





 



}
