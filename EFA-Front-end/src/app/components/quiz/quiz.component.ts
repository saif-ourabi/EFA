import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizeService } from 'src/app/services/quize.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  quize = [];
  repp = [];
  matiere: string;
  cour: string;
  score: number = 0;
  scoreShown: boolean = false;

  constructor(private quiz: QuizeService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.cour = params['cour'];
      console.log(this.cour);
      this.quiz.geteQuiz().subscribe((rep) => {
        this.quize = rep;
        this.matiere = rep.matiere;
        this.repp = this.fusion();
      });
    });
  }

  fusion() {
    return this.quize.filter(q => q.cour === this.cour);
  }

  calculateScore(rep: string, nu: number): number {
    let note: number = 0;
    const responses: string[] = rep.split('|');
    for (let i = 0; i < responses.length; i++) {
      if (i === nu) {
        note++;
      } else {
        note--;
      }
    }
    console.log(note);
    return note;
  }

  checkAnswer(rep: number, selectedOptionIndex: number): void {
    if (selectedOptionIndex === rep) {
      console.log('Correct answer selected for question:');
      this.score++; // Increment score if correct answer selected
    } else {
      console.log('Incorrect answer selected for question:');
    }
  }

  showScore(): void {
    this.scoreShown = true;
  }
}
