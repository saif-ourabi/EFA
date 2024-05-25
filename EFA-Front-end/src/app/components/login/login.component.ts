import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  c = false;
  loginError = '';

  constructor(private formBuilder: FormBuilder, 
    private loginService: LoginService,
    private toast: NgToastService,
    private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit() {
    this.c = true;
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value).subscribe(
        (rep: any) => {
          if (rep.status) {
            this.toast.success({detail:"SUCCÈS",summary:'Vous êtes connecté',duration:5000})
            console.log("logged in successfully");
            
            this.router.navigate(['/home']);


          } else {
            this.toast.error({detail:"ERROR",summary:"Mot de passe ou email incorrect"});
          }
        },
        (error) => {
          console.log("An error occurred:", error);
        }
      );
    }
  }
}
