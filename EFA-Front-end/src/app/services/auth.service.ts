import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  private authStatusSubject = new BehaviorSubject<boolean>(false);

  authStatus$ = this.authStatusSubject.asObservable();
  updateAuthStatus(): void {
    try {
      const jwtToken = sessionStorage.getItem("token");
      if (jwtToken === null) {
        this.authStatusSubject.next(false);
      } else {
        this.authStatusSubject.next(true);
      }
    } catch (error) {
      console.error('Error while checking authentication status:', error);
    }
  }

  logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('userId');
    this.authStatusSubject.next(false);
  }
}
