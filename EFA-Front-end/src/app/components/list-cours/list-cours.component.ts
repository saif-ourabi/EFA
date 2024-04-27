import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgToastService } from 'ng-angular-popup';
import { CoursService } from 'src/app/services/cours.service';
import { RatingService } from 'src/app/services/rating.service';

@Component({
  selector: 'app-list-cours',
  templateUrl: './list-cours.component.html',
  styleUrls: ['./list-cours.component.css']
})
export class ListCoursComponent implements OnInit {
  cours = [];
  pdfurl="https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf"
  feedbackform:FormGroup
  constructor(private course: CoursService, private toast: NgToastService, private router: Router,private modalservice: NgbModal,private formBuilder: FormBuilder,private rate:RatingService) {
    this.feedbackform = this.formBuilder.group({
      fileId: ['', Validators.required],
      stars: [, [Validators.required]],
      description: ['', Validators.required],
      email: ['ourabisaif48@gmail.com', Validators.required],
    });

  }
  @ViewChild('content') popupview !: ElementRef;
  @ViewChild('content1') popupview1 !: ElementRef;

  ngOnInit(): void {
    this.course.getefiles().subscribe((rep) => {
      this.cours = rep;
    });
  }

  downloadPDF(pdf) {
    const byteCharacters = atob(pdf.urlFile);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], { type: 'application/pdf' });
    const url = URL.createObjectURL(blob);
    const downloadLink = document.createElement('a');
    downloadLink.href = url;
    downloadLink.download = pdf.nameFile;
    downloadLink.click();
  }

  preview(base64Pdf) {
    base64Pdf = base64Pdf.urlFile;
    const byteCharacters = atob(base64Pdf);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], { type: 'application/pdf' });
    const url = URL.createObjectURL(blob);
    this.pdfurl = url;
    this.modalservice.open(this.popupview, { size: 'lg' });
  }

  feedback(c){
    this.modalservice.open(this.popupview1, { size: 'lg' });
    this.feedbackform.get("fileId").setValue(c)
  }
  submit(){
    {
      this.rate.addRating(this.feedbackform.value).subscribe((rep)=>
      {
        console.log(this.feedbackform.value)
        console.log(rep)
      })
    }
  }

}
