import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursService } from 'src/app/services/cours.service';

@Component({
  selector: 'app-update-file',
  templateUrl: './update-file.component.html',
  styleUrls: ['./update-file.component.css']
})
export class UpdateFileComponent implements OnInit {
  id: string;
  nameFile: string;
  imgFile: string;
  urlFile: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private coursService: CoursService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
  }

  updateFile(): void {
    const updatedFileData = {
      id: this.id,
      nameFile: this.nameFile,
      imgFile: this.imgFile,
      urlFile: this.urlFile
    };

    this.coursService.updateFile(this.id, updatedFileData).subscribe(
      response => {
        console.log('File updated successfully:', response);
        this.router.navigate(['/crudcours']); // Replace with your desired route
      },
      error => {
        console.error('Error updating file:', error);
      }
    );
  }
}
