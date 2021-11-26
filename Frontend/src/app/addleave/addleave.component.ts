import { Component, OnInit } from '@angular/core';
import { Employee } from '../models/employee';
import { Leaves } from '../models/leaves.models';
import { DetailsService } from '../_services/details.service';
import { LeaveService } from '../_services/leave.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-addleave',
  templateUrl: './addleave.component.html',
  styleUrls: ['./addleave.component.css']
})
export class AddleaveComponent implements OnInit {
  leave: Leaves = {
    des:'',
    dt:'',
    username:'',
  }
  errors={
    error:''
  }
  detail : Employee = {
    username : "",
    sick:"",
    casual:"",
    privilege:""
  }
  check=false
  error!:string
  details!:Employee[];
  username!:""
  type!:string
  noofdays!:String
  constructor(private leaveService:LeaveService , 
    private tokenStorageService:TokenStorageService,
    private detailsService:DetailsService) { }

  onSubmit(): void{
    window.sessionStorage.clear();
    window.location.href = 'login'
  }

  ngOnInit(): void {
    this.username = this.tokenStorageService.getUser().username;
    //console.log(this.username.toString())
    this.retriveDetails(this.username)
  }
  retriveDetails(username:string): void{
    username = this.tokenStorageService.getUser().username; 

     this.detailsService.getUser(username).subscribe(
      data =>{this.details=data;
        console.log(this.details)}

     )
  }

  saveLeave(): void{
    console.log(this.type);
    console.log(this.noofdays)
    this.leaveService.check(this.username,this.type,this.noofdays).subscribe(
      response=>{
        if(response){
          this.check=true;
          this.leaveService.return(this.username,this.type,this.noofdays).subscribe(
            data=>{
              console.log(data);
              this.errors=data;
            }
          )
        }
        else{
          const data = {
            des:this.leave.des,
            dt:this.leave.dt,
            username:this.username,
            type:this.type,
            status:"pending",
            noofdays:this.noofdays
          };
          console.log(data)
          
          this.leaveService.addLeave(data).subscribe(response => {console.log(response)}) 
          this.leaveService.update(this.username,this.type,this.noofdays).subscribe(response=>{console.log(response)})
          window.location.href = 'home'

        }
      }
    )
    
  }

 
}
