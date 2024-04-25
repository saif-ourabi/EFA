import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QuizeService } from 'src/app/services/quize.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit{
  quize=[];
  matiere: String;
  cour:String;
  selectedMatiere: string;
  selectedCour: string;
  correctAnswers: number = 0;
  totalQuestions: number = 0;
  constructor(private quiz:QuizeService,private router:Router){
   }
  ngOnInit(): void {
    this.quiz.geteQuiz().subscribe((rep)=>{
      this.quize=rep
      this.matiere=rep.matiere
      this.cour=rep.cour;
    })
  }

  selectMatiere(matiere: string): void {
    this.selectedMatiere = matiere;
  }

  selectCour(cour: string): void {
    this.selectedCour = cour;
    this.correctAnswers = 0; 
    this.totalQuestions = this.quize.filter(item => item.cour === this.selectedCour).length;
  }

  checkAnswer(selectedIndex: number, correctIndex: number): void {
    if (selectedIndex === correctIndex) {
      this.correctAnswers++;
    }
  }

  calculateAverage(): number {
    if (this.totalQuestions === 0) {
      return 0;
    }
    return (this.correctAnswers / this.totalQuestions) * 100;
  }
}


