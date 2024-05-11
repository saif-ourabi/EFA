import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isAuthenticated: boolean=false;
  user:any
  constructor(private authService: LoginService) {}
  ngOnInit(): void {
    this.authService.checkAuthStatus()
    this.authService.authStatus$.subscribe((isLoggedIn: boolean) => {
      this.isAuthenticated = isLoggedIn;
    })
    
    this.authService.getUserInfo().subscribe((rep)=>{
      this.user=rep
      console.log(rep)
    })
  }

  Logout():void{
    this.authService.logout();
  }
}
