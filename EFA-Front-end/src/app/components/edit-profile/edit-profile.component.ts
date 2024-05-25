import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
})
export class EditProfileComponent implements OnInit {
  user: any;
  editForm: FormGroup;
  confirmeuser: FormGroup;
  c = false;
  userIn;

  constructor(
    private authService: LoginService,
    private formBuilder: FormBuilder,
    private toast: NgToastService,
    private loginService: LoginService
  ) {
    this.confirmeuser = this.formBuilder.group({
      email: [''],
      password: ['', Validators.required],
    });

    this.editForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+$/)]],
      role: ['', Validators.required],
      email: [''],
      id: [''],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.authService.getUserInfo().subscribe((rep) => {
      this.user = rep;
      console.log(rep);
    });
  }

  onSubmit() {
    this.c = true;
    if (this.confirmeuser.valid) {
      this.confirmeuser.get('email').setValue(this.user.email);
      console.log(this.confirmeuser.value);
      this.loginService.login(this.confirmeuser.value).subscribe(
        (rep: any) => {
          if (rep.status) {
            if (this.editForm.valid) console.log(this.editForm);
            this.editForm.get('id').setValue(this.user.id);
            this.editForm.get('email').setValue(this.user.email);
            this.authService.edit(this.editForm.value).subscribe((rep) => {
              if (rep) {
                this.toast.success({
                  detail: 'SUCCÈS',
                  summary: 'user info updated',
                  duration: 5000,
                });
              }
            });
          } else {
            this.toast.error({
              detail: 'ERROR',
              summary: 'Mot de passe incorrect',
            });
          }
        },
        (error) => {
          console.log('An error occurred:', error);
        }
      );
    }
  }
}
