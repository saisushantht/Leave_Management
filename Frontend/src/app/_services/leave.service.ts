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

  getAll(): Observable<Leaves[]>{
   return this.http.get<Leaves[]>(URL + 'getAll')
  }

  update(username:string,type:string,noofdays:String):Observable<any>{
    return this.http.post(URL +`update/${username}`,{type,noofdays})
  }

  updatemanager(username:any,type:any,noofdays:any):Observable<any>{
    return this.http.post(URL +`updatemanager/${username}`,{type,noofdays})
  }

  check(username:string,type:string,noofdays:String):Observable<any>{
    return this.http.post(URL +`check/${username}`,{type,noofdays})
  }

  return(username:string,type:string,noofdays:String):Observable<any>{
    return this.http.post(URL +`return/${username}`,{type,noofdays})
  }

}
