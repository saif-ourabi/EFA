import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) {}
  private url="http://localhost:8087/api/user/register"
  registerUser(userData: any): Observable<any> {
    return this.http.post(this.url, userData);
  }
}
