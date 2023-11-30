import { Component, OnInit } from '@angular/core';
import { MarvelServiceService } from '../../service/marvel-service.service';
import { MatTableModule } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-heroes',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './heroes.component.html',
  styleUrl: './heroes.component.scss'
})
export class HeroesComponent implements OnInit {
  title = 'angular-marvel';
  dataSource: any[] = [];
  displayedColumns: string[] = ['id', 'nombre'];

  constructor(private marvelServiceService: MarvelServiceService, public dialog: MatDialog) { }

  ngOnInit() {
    this.getHeroes();
  }

  getHeroes(): void {
    this.marvelServiceService.getHeroes().subscribe(
      (data) => {
        this.dataSource = data;
      },
      (error) => {
        console.error('Error', error);
      }
    );
  }

  getHeroesById(id: any): void {
    this.marvelServiceService.getHeroById(id).subscribe(
      (data) => {

        const dialogRef = this.dialog.open(DialogComponent, {
          width: '400vw',
          data: data
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log(`El diálogo se cerró con resultado: ${result}`);
        });

      },
      (error) => {
        console.error('Error', error);
      }
    );

  }
}
