import { Component, ChangeDetectionStrategy, OnInit, Input } from '@angular/core';

import { Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
//import { GuardService } from '../../servicies/guard.service';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {FormBuilder,FormGroup,Validators,ReactiveFormsModule} from "@angular/forms";
import { UsuarioService } from '../../services/usuario.service';
import { GuardService } from '../../guard.service';
//import { LoginService } from '../../servicies/login.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  @Input() usarLogout!: boolean;

  constructor(
    private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private guardService: GuardService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      loguin: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  async logar(){
    const vm = this;
    const loginForm = this.loginForm.value;
    this.usuarioService.loguinAsync(loginForm).then(async(data) => {
      if (!data.id) {
        alert("usuario ou senha não existem");
        //vm.guardService.logout();
        localStorage.removeItem("logado");
        localStorage.removeItem("usuarioLoguin");
        return;
      }
      localStorage.setItem("logado", "true");
      this.usuarioService.usuarioLoguin = data;
      const token = data.sub;
      localStorage.setItem("usuarioLoguin", JSON.stringify(data));
      localStorage.setItem("token", token);
      //vm.guardService.login();
      vm.router.navigate([`processos`]);
    });
  }

  logout(){
    //this.guardService.logout();
    localStorage.removeItem("logado");
    localStorage.removeItem("usuarioLoguin");
    this.router.navigate([`loguin`]);
  }
}
