import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Leaves } from '../models/leaves.models';
import { LeaveService } from '../_services/leave.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  leave?:Leaves[];
  dt='';
  des='';
  username=''
  


  constructor(private leaveService:LeaveService, private route:ActivatedRoute, private router:Router ) { }

  

  ngOnInit(): void {
    this.leaveService.getAll().subscribe(
      data=>{
        this.leave=data;
        console.log(data);
      }
    )
  }

  onapprove(leave:Leaves){
    leave.status="approved"
    const data = {
      des:leave.des,
      dt:leave.dt,
      username:leave.username,
      type:leave.type,
      noofdays:leave.noofdays,
      status:"approved"
    };
    this.leaveService.addLeave(data).subscribe(response => {console.log(response)}) 
    //window.location.reload();

  }
  onreject(leave:Leaves){
    leave.status="rejected"
    const data = {
      des:leave.des,
      dt:leave.dt,
      username:leave.username,
      type:leave.type,
      noofdays:leave.noofdays,
      status:"rejected"
    };
    console.log(data)
    this.leaveService.updatemanager(data.username,data.type,data.noofdays).subscribe();
    this.leaveService.addLeave(data).subscribe(response => {console.log(response)})
    //window.location.reload(); 
    
  }

}
