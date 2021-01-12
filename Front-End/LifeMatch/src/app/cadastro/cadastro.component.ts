import { MidiaServiceService } from './../midia-service.service';
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
  tipoUsuario: string

  imagem: File

  constructor(
    private authService: AuthService,
    private router: Router,
    private alert: AlertasService,
    private midiaService: MidiaServiceService


  ) { }

  ngOnInit(){
    window.scroll(0,0)
  }

  conferirSenha(event: any){
    this.senha = event.target.value
  }

  carregarImagemPreview(event: any) {
    this.imagem = event.target.files[0]
    let url = URL.createObjectURL(this.imagem);
    (<HTMLImageElement>document.querySelector('img#imagem-preview'))!.src = url
  }


  tipoUser(event: any){
    this.tipoUsuario = event.target.value
  }



  cadastrar(){
    this.usuario.tipo = this.tipoUsuario
    if(this.senha === this.usuario.senha){
      if (this.imagem) {
          this.midiaService.uploadPhoto(this.imagem).subscribe((resp: any) => {
          
          this.usuario.imagem = resp.secure_url  
          this.authService.cadastrar(this.usuario).subscribe((resp:Usuario) => {
          this.usuario = resp
          this.router.navigate(["/login"])
          this.alert.showAlertSuccess('Usuario cadastrado com sucesso')
        })

        })
      }
      else {
        // Cadastrar uma imagem Padrão
      }
     
    }    
    else {
      
      this.alert.showAlertDanger('Suas senhas não conferem')
    }
  }

}
