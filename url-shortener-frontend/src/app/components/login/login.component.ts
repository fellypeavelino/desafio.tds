import { Component, ChangeDetectionStrategy, OnInit, Input } from '@angular/core';

import { Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {FormBuilder,FormGroup,Validators,ReactiveFormsModule} from "@angular/forms";
import { UsuarioService } from '../../services/usuario.service';
import { GuardService } from '../../guard.service';

@Component({
  selector: 'app-login',
  imports: [
    MatIconModule, MatDividerModule, MatFormFieldModule, 
    MatInputModule, MatButtonModule,
    MatSelectModule, MatCardModule, ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
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
      loguin: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(50)]],
      senha: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  async logar(){
    const vm = this;
    const loginForm = this.loginForm.value;
    this.usuarioService.loguinAsync(loginForm).then(async(data) => {
      const {usuarioDto} = data;
      if (!usuarioDto.id) {
        alert("usuario ou senha não existem");
        vm.guardService.logout();
        localStorage.removeItem("logado");
        localStorage.removeItem("usuarioLoguin");
        return;
      }
      localStorage.setItem("logado", "true");
      this.usuarioService.usuarioLoguin = usuarioDto;
      const token = data.sub;
      localStorage.setItem("usuarioLoguin", JSON.stringify(data));
      localStorage.setItem("token", token);
      vm.guardService.login();
      vm.router.navigate([`usuarios`]);
    });
  }

  logout(){
    this.guardService.logout();
    localStorage.removeItem("logado");
    localStorage.removeItem("usuarioLoguin");
    this.router.navigate([`loguin`]);
  }
}
