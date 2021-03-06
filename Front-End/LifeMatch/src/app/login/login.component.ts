import { environment } from './../../environments/environment.prod';

import { UsuarioLogin } from '../model/UsuarioLogin';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuarioLogin: UsuarioLogin = new UsuarioLogin()
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }


  entrar() {
    this.authService.logar(this.usuarioLogin).subscribe((resp: UsuarioLogin) => {
      this.usuarioLogin = resp

      environment.nome =this.usuarioLogin.nome //pegando o nome do usuario que está logado para mostra na tela
      environment.imagem = this.usuarioLogin.imagem
      environment.token = this.usuarioLogin.token
      environment.tipo = this.usuarioLogin.tipo
      this.router.navigate(['/painel'])
    })


}
}
