import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  status: boolean = false;
  constructor(private authService: AuthService) {}
  ngOnInit(): void {
    this.authService.updateAuthStatus();
    this.authService.authStatus$.subscribe((status: boolean) => {
      this.status = status;
    });
  }

  Logout():void{
    this.authService.logout();
  }
}
