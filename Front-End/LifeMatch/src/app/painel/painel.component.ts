import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { Usuario } from '../model/Usuario';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';
import { AlertasService } from './../service/alertas.service';
import { environment } from './../../environments/environment.prod';
import { UsuarioLogin } from './../model/UsuarioLogin';

@Component({
  selector: 'app-painel',
  templateUrl: './painel.component.html',
  styleUrls: ['./painel.component.css']
})
export class PainelComponent implements OnInit {

  key = 'data'
  reverse = true

  //instânciando um objeto Postagem e atribuindo a variavel postagem
  postagem: Postagem = new Postagem()

  //criando uma lista de Postagens
  listaPostagens: Postagem[]

  tema: Tema = new Tema()
  listaTemas: Tema[]
  titulo: string
  categoriaAjuda: string

  usuario: Usuario = new Usuario()
  
  
  
  //Injetando as dependências do service.
  constructor(
    private postagemService: PostagemService,
    private temaService: TemaService,
    private router: Router,
    private alerta: AlertasService
  ) { }

  ngOnInit(){
    
    let token = environment.token

    if(token == '') {
      this.router.navigate(['/login'])
      this.alerta.showAlertInfo('Faça o login antes de entrar no feed...')
    }

    window.scroll(0,0)

    //chamando meus métodos para serem utilizados assim que a página html for renderizada.
    this.findAllPostagens()
    this.findAllTemas()
  }

  //criando um método para encontrar todas as Postagens e retornando em lista
  findAllPostagens(){
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[])=>{
      this.listaPostagens = resp
    })
  }

  //criando um método para encontrar todos os Temas e retornando em lista
  findAllTemas(){
    this.temaService.getAllTemas().subscribe((resp: Tema[])=>{
      this.listaTemas = resp
    })
  }

  findByTituloPostagem()
  {
    if(this.titulo === ''){
      this.findAllPostagens();
    }
    else{
    this.postagemService.getByTituloPostagem(this.titulo).subscribe((resp:Postagem[])=>{
      this.listaPostagens = resp}); 
    }
  }

  findByNomeTema(){
    if(this.categoriaAjuda === ''){
      this.findAllTemas()
    } else {
      this.temaService.getByNomeTema(this.categoriaAjuda).subscribe((resp: Tema[]) => {
        this.listaTemas = resp
      })
    }
  }

}
