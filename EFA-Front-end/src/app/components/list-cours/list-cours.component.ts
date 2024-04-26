import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgToastService } from 'ng-angular-popup';
import { pdfDefaultOptions } from 'ngx-extended-pdf-viewer';
import { CoursService } from 'src/app/services/cours.service';

@Component({
  selector: 'app-list-cours',
  templateUrl: './list-cours.component.html',
  styleUrls: ['./list-cours.component.css']
})
export class ListCoursComponent implements OnInit {
  cours = [];
  pdfurl="https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf"
  constructor(private course: CoursService, private toast: NgToastService, private router: Router,private modalservice: NgbModal) {
  }
  @ViewChild('content') popupview !: ElementRef;

  ngOnInit(): void {
    this.course.getefiles().subscribe((rep) => {
      this.cours = rep;
      console.log(rep);
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

}
