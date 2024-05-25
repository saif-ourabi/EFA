import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursService } from 'src/app/services/cours.service';
import { RatingService } from 'src/app/services/rating.service';
import { forkJoin } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  id = "";
  course: any;
  filteredRatings: any[] = [];

  constructor(
    private courseService: CoursService,
    private ratingService: RatingService,
    private route: ActivatedRoute,
    private authService: LoginService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      if (this.id) {
        forkJoin([
          this.courseService.getefiles(),
          this.ratingService.getRatings()
        ]).subscribe(([courses, ratings]) => {
          this.course = courses.find((course: any) => course.id === +this.id);
          console.log('Course:', this.course);
          this.filteredRatings = ratings.filter((rating: any) => rating.file && rating.file.id === +this.id);
          this.filteredRatings.forEach(rating => {
            if (rating.user) {
              rating.userEmail = rating.user.email;
            } else {
              rating.userEmail = 'Anonymous';
            }
          });
          console.log('Filtered Ratings:', this.filteredRatings);
        });
      }
    });
  }
}
