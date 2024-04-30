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
  nb: number=0;
  test:boolean =true;

  constructor(private quiz: QuizeService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.cour = params['cour'];
      console.log(this.cour);
      this.quiz.geteQuiz().subscribe((rep) => {
        this.quize = rep;
        this.matiere = rep.matiere;
        this.repp = this.fusion();
        this.nb = this.repp.length;
      });
    });
  }

  fusion() {
    return this.quize.filter(q => q.cour === this.cour);
  }
  resetAnswers(): void {
    this.repp.forEach(question => {
      question.selectedAnswerIndex = undefined; 
    });
    this.score = 0; 
    this.scoreShown = false; 
  }
  cancelAnswer(radioBtn): void {
    radioBtn.checked = false; 
  }
  resetRadioButtons(): void {
    const radioButtons = document.querySelectorAll('input[type="radio"]');
    radioButtons.forEach((button: HTMLInputElement) => {
      button.checked = false;
    });
  }
  calculateScore(rep: string, nu: number): number {
    let note: number = 0;
    const responses: string[] = rep.split('|');
    for (let i = 0; i < responses.length; i++) {
      if (i === nu) {
        note++;
        if(note==this.nb ){
          this.test=false;
          return note;
        }
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
      if(this.score==this.nb ){
        this.test=false;
        
      }
      if(this.test==true){
      this.score++; }
      
    } else {
      
      console.log('Incorrect answer selected for question:');
    }
  }

  showScore(): void {
    this.scoreShown = true;
  }
}
