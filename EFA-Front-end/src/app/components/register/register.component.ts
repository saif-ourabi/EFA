import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registrationForm: FormGroup;
  c = false;

  constructor(private formBuilder: FormBuilder, private registrationService: RegistrationService,private toast: NgToastService,private router: Router) {
    this.registrationForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+$/)]],
      role: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    this.c = true;
    if (this.registrationForm.valid) {
      this.registrationService.registerUser(this.registrationForm.value)
        .subscribe(
          (rep: any) => {
            if (rep.status) {
              this.toast.success({detail:"SUCCÈS",summary:'user added ',duration:5000})
              this.router.navigate(['/login']);
              this.registrationForm.reset();
            } else {
              console.log('user already exists');
              this.toast.warning({detail:"WARNING",summary:'user already exists',duration:5000})
            }
          },
          (error) => {
            console.error("error", error);
          }
        );
    }
  }

  onReset() {
    this.c = false;
    this.registrationForm.reset();
  }
}
