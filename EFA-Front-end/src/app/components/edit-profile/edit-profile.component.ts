import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit{
  user:any
  editFrom: FormGroup;
  c = false;
  constructor(private authService: LoginService,private formBuilder: FormBuilder,private toast: NgToastService) {
    this.editFrom = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+$/)]],
      role: ['', Validators.required],
      email: [""],
      id:[""],
      password: ['', Validators.required]
    })
  }
  ngOnInit(): void {
    this.authService.getUserInfo().subscribe((rep)=>{
      this.user=rep
      console.log(rep)
    })
  }

  onSubmit(){
    this.c=true
    if(this.editFrom.valid)
      console.log(this.editFrom)
      this.editFrom.get('id').setValue(this.user.id);
      this.authService.edit(this.editFrom.value).subscribe((rep)=>{
        if(rep){
          this.toast.success({detail:"SUCCÈS",summary:'user info updated',duration:5000})
        }
      })
  }
  
}
