import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  
  private apiUrl = 'http://localhost:8081/api/user/';
  private authStatusSubject = new BehaviorSubject<boolean>(false);
  authStatus$ = this.authStatusSubject.asObservable();

  constructor(private http: HttpClient) {}

  login(userData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}login`, userData).pipe(
      tap((response: any) => {
        if (response.status) {
          this.setSessionData(response.token);
          this.authStatusSubject.next(true);
        } else {
          this.authStatusSubject.next(false);
        }
      }),
      catchError((error) => {
        console.error('Login failed:', error);
        return throwError(error);
      })
    );
  }

  getUserInfo(): Observable<any> {
    const token = sessionStorage.getItem('token');
    if (token) {
      return this.http.get(`${this.apiUrl}decodeToken?token=${token}`).pipe(
        catchError(error => {
          console.error('Error decoding token:', error);
          return throwError(error);
        })
      );
    } else {
      return of(null);
    }
  }
  
  logout(): void {
    sessionStorage.removeItem('token');
    this.authStatusSubject.next(false);
  }

  checkAuthStatus(): void {
    const jwtToken = sessionStorage.getItem('token');
    if (jwtToken) {
      this.http.get<any>(`${this.apiUrl}verify?token=${jwtToken}`).subscribe(
        (response: any) => {
          this.authStatusSubject.next(true);
        },
        (error) => {
          console.error('Error checking authentication status:', error);
          this.authStatusSubject.next(false);
        }
      );
    }
  }

  private setSessionData(token: string): void {
    sessionStorage.setItem('token', token);
  }

  edit(userData: any): Observable<any> {
    return this.http.put(this.apiUrl+'update',userData)
  }
}
