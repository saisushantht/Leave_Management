import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Details } from '../models/details.model';

const URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class DetailsService {

  constructor(private http:HttpClient) { }

  getUserName(username:string): Observable<Details[]>{
    return this.http.get<Details[]>(URL + `home/${username}`)
  }
}
