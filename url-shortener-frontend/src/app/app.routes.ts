import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { UsuarioListComponent } from './components/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: 'loguin', component: LoginComponent },
    {path: 'usuarios', component: UsuarioListComponent, canActivate: [authGuard]},
    {path: 'usuario-form', component: UsuarioFormComponent, canActivate: [authGuard]},
    {path: 'usuario-form/:id', component: UsuarioFormComponent, canActivate: [authGuard]},
    // { path: 'processos/novo', component: FormProcessoComponent, canActivate: [authGuard]  },
    // { path: 'processos/editar/:id', component: FormProcessoComponent, canActivate: [authGuard]  },
    { path: '', redirectTo: '/loguin', pathMatch: 'full' }, 
    { path: '**', redirectTo: '/loguin' } 
];
