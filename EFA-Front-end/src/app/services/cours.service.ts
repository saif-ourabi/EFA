import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursService {

  constructor(private http: HttpClient) { }
  private url="http://localhost:8081/api/files"
  getefiles(): Observable<any> {
    return this.http.get(this.url);
}
}
