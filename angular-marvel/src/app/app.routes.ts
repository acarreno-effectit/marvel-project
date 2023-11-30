import { Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { HeroesComponent } from './components/heroes/heroes.component';
import { LogsComponent } from './components/logs/logs.component';

export const routes: Routes = [
    { path: 'menu', component: MenuComponent },
    { path: 'heroes', component: HeroesComponent },
    { path: 'logs', component: LogsComponent },
];
