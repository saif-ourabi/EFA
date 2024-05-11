import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CoursService } from 'src/app/services/cours.service';

@Component({
  selector: 'app-add-file',
  templateUrl: './add-file.component.html',
  styleUrls: ['./add-file.component.css']
})
export class AddFileComponent {
  id: string;
  nameFile: string;
  imgFile: string;
  urlFile: string;

  constructor(private http: HttpClient, private coursService: CoursService) {}

  addFile(): void {
    const formData = {
      id: this.id,
      nameFile: this.nameFile,
      imgFile: this.imgFile,
      urlFile: this.urlFile
    };

    this.coursService.addFile(formData).subscribe(
      response => {
        console.log('File added successfully:', response);
        // Reset form fields after successful submission if needed
        this.id = '';
        this.nameFile = '';
        this.imgFile = '';
        this.urlFile = '';
      },
      error => {
        console.error('Error adding file:', error);
        // Handle error message display or other actions
      }
    );
  }

  
}
