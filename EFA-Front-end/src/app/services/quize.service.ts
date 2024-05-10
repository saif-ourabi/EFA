import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuizeService {

  constructor(private http:HttpClient) {}
   private url="http://localhost:8081/api/quiz"
   geteQuiz():Observable<any>{
    return this.http.get(this.url);
   }
}
