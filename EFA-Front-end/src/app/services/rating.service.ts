import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private baseUrl = 'http://localhost:8081/api/files/rating';

  constructor(private http: HttpClient) { }

  getRatings(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl + '/all')
  }

  getRatingById(id: number): Observable<any> {
    const url = `${this.baseUrl}/all/${id}`;
    return this.http.get<any>(url)
  }

  addRating(data):Observable<any>{
    return this.http.post<any>(this.baseUrl,data)
  }


}
