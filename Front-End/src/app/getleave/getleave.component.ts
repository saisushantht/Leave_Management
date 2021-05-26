import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Leaves } from '../models/leaves.models';
import { LeaveService } from '../_services/leave.service';

@Component({
  selector: 'app-getleave',
  templateUrl: './getleave.component.html',
  styleUrls: ['./getleave.component.css']
})
export class GetleaveComponent implements OnInit {
  leave?:Leaves[];
  dt='';
  des='';
  username=''


  constructor(private leaveService:LeaveService, private route:ActivatedRoute, private router:Router ) { }

  onSubmit(): void{
    window.sessionStorage.clear();
    window.location.href = 'login'
  }

  ngOnInit(): void {
    this.retriveLeaves(this.route.snapshot.params.employee_id)
  }

  retriveLeaves(username:string):void{
    this.leaveService.getLeaveById(username).subscribe(
      data =>{this.leave=data;
      console.log(data)}
    )
  }


}
