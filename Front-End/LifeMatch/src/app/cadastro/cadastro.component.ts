import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../model/Usuario';
import { AuthService } from '../service/auth.service';
import { AlertasService } from './../service/alertas.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  usuario: Usuario = new Usuario()
  senha: string

  constructor(
    private authService: AuthService,
    private router: Router,
    private alert: AlertasService
  ) { }

  ngOnInit(): void {
  }

  conferirSenha(event: any){
    this.senha = event.target.value
  }

  cadastrar(){
    if(this.senha === this.usuario.senha){
      this.authService.cadastrar(this.usuario).subscribe((resp:Usuario) => {
        this.usuario = resp
        this.router.navigate(["/login"])
        this.alert.showAlertSuccess('Usuario cadastrado com sucesso')
      })
    } else {
      this.alert.showAlertDanger('Suas senhas não conferem')
    }
  }

}
