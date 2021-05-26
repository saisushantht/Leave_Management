import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Details } from '../models/details.model';

import { Leaves } from '../models/leaves.models';


const URL = "http://localhost:8080/"
@Injectable({
  providedIn: 'root'
})
export class LeaveService {

  constructor(private http:HttpClient) {}
  getLeaveById(username:string): Observable<Leaves[]> {
    return this.http.get<Leaves[]>(URL + `getLeaves/${username}`)
  }

  addLeave(data:any): Observable<any>{
    return this.http.post(URL + 'addLeave',data)
  }



}
