import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    { path: 'loguin', component: LoginComponent },
    // { path: 'processos', component: ProcessosComponent, canActivate: [authGuard]  },
    // { path: 'processos/novo', component: FormProcessoComponent, canActivate: [authGuard]  },
    // { path: 'processos/editar/:id', component: FormProcessoComponent, canActivate: [authGuard]  },
    { path: '', redirectTo: '/loguin', pathMatch: 'full' }, 
    { path: '**', redirectTo: '/loguin' } 
];
