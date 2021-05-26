import { Component, OnInit } from '@angular/core';
import { Leaves } from '../models/leaves.models';
import { LeaveService } from '../_services/leave.service';

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
  constructor(private leaveService:LeaveService , ) { }

  onSubmit(): void{
    window.sessionStorage.clear();
    window.location.href = 'login'
  }

  ngOnInit(): void {
  }

  saveLeave(): void{
    const data = {
      des:this.leave.des,
      dt:this.leave.dt,
      username:this.leave.username
    };
    this.leaveService.addLeave(data).subscribe(response => {console.log(response)}) 
    window.location.href = 'home'
  }
}
