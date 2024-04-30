import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizeService } from 'src/app/services/quize.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit{
  quize=[];
  repp=[];
  matiere: String;
  cour:String;
  score: number = 0;
  scoreShown: boolean = false;
  constructor(private quiz:QuizeService,private router:Router,private route: ActivatedRoute){
   }
  
  
   ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.cour = params['cour']; 
      console.log(this.cour);
      this.quiz.geteQuiz().subscribe((rep) => {
        this.quize = rep;
        this.matiere = rep.matiere;
        this.repp=this.fusion();
      });
    });
  }
  
  fusion() {
     let ra = [];
     let j=0;
    console.log(this.cour);
    for (let i = 0; i < this.quize.length; i++) {
      if (this.quize[i].cour === this.cour) {
        console.log(this.quize[i].cour);
        ra[j]=this.quize[i].cour;
        j++;
        console.log("j:",j);
        console.log(ra[j]);
      }
    }
    console.log("ra:", ra);
    return ra;
  }
  
  
  

  calculateScore(rep: string, nu: number): number {
    let note: number = 0; 
    const responses: string[] = rep.split('|'); 
    for (let i = 0; i < responses.length; i++) {
      if (i === nu) {
        note++;
      }
      else{
        note--;
      }
    }

    console.log(note);
    return note; 
  }
  checkAnswer(rep:number, selectedOptionIndex: number):void {
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



