import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Details } from '../models/details.model';
import { DetailsService } from '../_services/details.service';
import { LeaveService } from '../_services/leave.service';
import { TokenStorageService } from '../_services/token-storage.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  detail : Details = {
    username : "",
    no_of_leaves : ""
  }
  details?:Details[];
    username = "";
    
    leaves_check = true;
    count!:Number;


  constructor(private detailsService:DetailsService , private route:ActivatedRoute , private router:Router,private tokenStorageService : TokenStorageService) { }

  onSubmit(): void{
    window.sessionStorage.clear();
    window.location.href = 'login'
  }
  ngOnInit(): void {
    this.username = this.tokenStorageService.getUser().username;
    //console.log(this.username.toString())
    this.retriveDetails(this.username)

    this.detailsService.count(this.username).subscribe(
      data=>{
        this.count=data
        console.log(data);
      }
    )

  }
  check(leaves_left:number):any{
    if(leaves_left==11){
      this.leaves_check = false;
      alert("You have exhausted all your leaves");
    }
    else if(leaves_left<5){
      alert("You have only "+leaves_left+" leaves left ")

    }


  }

  retriveDetails(username:string): void{
    username = this.tokenStorageService.getUser().username; 

     this.detailsService.getUserName(username).subscribe(
      data =>{this.details=data;
        console.log(this.details)}

     )
  }





 



}
