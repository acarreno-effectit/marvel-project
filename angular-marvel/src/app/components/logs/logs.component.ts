import { Component, OnInit } from '@angular/core';
import { LogServiceService } from '../../service/log-service.service';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-logs',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './logs.component.html',
  styleUrl: './logs.component.scss'
})
export class LogsComponent implements OnInit {

  dataSource: any[] = [];
  displayedColumns: string[] = ['servicio', 'fecha', 'respuesta'];

  constructor(private logServiceService: LogServiceService) { }

  ngOnInit() {
    this.logServiceService.getLogs().subscribe(
      (data) => {
        this.dataSource = data;
      },
      (error) => {
        console.error('Error', error);
      }
    );
  }

}
