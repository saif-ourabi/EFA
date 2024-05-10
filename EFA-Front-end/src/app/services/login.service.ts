import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(
    !!sessionStorage.getItem('token')
  );

  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8081/api/user/login';
  private authStatusSubject = new BehaviorSubject<boolean>(false);
  authStatus$ = this.authStatusSubject.asObservable();

  login(userData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, userData).pipe(
      tap((response: any) => {
        if (response.status) {
          this.setSessionData(response);
        }
        this.authStatusSubject.next(response.status);
      }),
      catchError((error) => {
        console.error('Login failed:', error);
        return throwError(error);
      })
    );
  }

  get isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }

  checkAuthentication(): void {
    const token = sessionStorage.getItem('token');
    const isAuthenticated = !!token;
    this.isAuthenticatedSubject.next(isAuthenticated);
  }

  private setSessionData(response: any): void {
    sessionStorage.setItem('token', response.token);
  }

  logout(): void {
    sessionStorage.removeItem('token');
    this.authStatusSubject.next(false);
  }

  checkAuthStatus(): void {
    const jwt_token = sessionStorage.getItem('token');
    if (jwt_token) {
      this.authStatusSubject.next(true);
    } else {
      this.authStatusSubject.next(false);
    }
  }
}
