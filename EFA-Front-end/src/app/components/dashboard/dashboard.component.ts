import { Component, OnInit } from '@angular/core';
import {Chart,registerables} from 'node_modules/chart.js'
import { CoursService } from 'src/app/services/cours.service';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private course: CoursService ){}

  chartdata:any;
  countdata:any[]=[];
  roledata:any[]=[];
  ngOnInit ():void
  {
    this.course.GetChartInfo().subscribe(result => {
      this.chartdata = result;
      if (this.chartdata!=null){
        for (let i=0; i<this.chartdata.length ; i++)
          {
            //console.log(this.chartdata[i]);
            this.countdata.push(this.chartdata[i].count);
            this.roledata.push(this.chartdata[i].role);
          }
          this.RenderChart(this.countdata, this.roledata);
      }
    });
    
  }
  RenderChart(countdata:any, roledata:any)
  {
    const ctx = document.getElementById('myChart');

  new Chart("piechart", {
    type: 'pie',
    data: {
      labels: roledata,
      datasets: [{
        label: '# of Votes',
        data: countdata,
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

  }

}
