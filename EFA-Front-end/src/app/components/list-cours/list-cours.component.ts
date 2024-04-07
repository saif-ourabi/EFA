import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CoursService } from 'src/app/services/cours.service';

@Component({
  selector: 'app-list-cours',
  templateUrl: './list-cours.component.html',
  styleUrls: ['./list-cours.component.css']
})
export class ListCoursComponent implements OnInit {
  cours=[];
  pdfData: string;
  constructor(private cors:CoursService, private toast: NgToastService,private router: Router){}
  ngOnInit(): void {
    this.cors.getefiles().subscribe((rep)=>{
      this.cours=rep
      this.pdfData = rep.urlFile;
    })
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
    downloadLink.download = 'PDF Title';
    downloadLink.click();
  }
  

  


}
