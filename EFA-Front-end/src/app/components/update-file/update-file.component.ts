import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

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

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    // Retrieve the file ID from the route parameter
    this.route.params.subscribe(params => {
      this.id = params['id'];
      // Fetch the file details using the ID (you need to implement this)
      this.fetchFileDetails();
    });
  }

  fetchFileDetails(): void {
    // Implement this method to fetch file details from your service
    // Example:
    // this.fileService.getFileById(this.id).subscribe(
    //   response => {
    //     this.nameFile = response.nameFile;
    //     this.imgFile = response.imgFile;
    //     this.urlFile = response.urlFile;
    //   },
    //   error => {
    //     console.error('Error fetching file details:', error);
    //     // Handle error message display or other actions
    //   }
    // );
  }


}
