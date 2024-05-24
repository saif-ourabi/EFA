import { Component } from '@angular/core';
import { CoursService } from 'src/app/services/cours.service';

@Component({
  selector: 'app-add-file',
  templateUrl: './add-file.component.html',
  styleUrls: ['./add-file.component.css']
})
export class AddFileComponent {
  id: string = '';
  nameFile: string = '';
  imgFile: string = '';
  urlFile: string = '';

  constructor(private coursService: CoursService) {}

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
        // Reset form fields after successful submission
        this.resetForm();
      },
      error => {
        console.error('Error adding file:', error);
        // Handle error message display or other actions
      }
    );
  }

  resetForm(): void {
    this.id = '';
    this.nameFile = '';
    this.imgFile = '';
    this.urlFile = '';
  }
}
