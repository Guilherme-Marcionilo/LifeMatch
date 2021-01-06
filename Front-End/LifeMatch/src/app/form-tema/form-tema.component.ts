import { environment } from './../../environments/environment.prod';
import { Postagem } from './../model/Postagem';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tema } from '../model/Tema';
import { TemaService } from '../service/tema.service';
import { AlertasService } from './../service/alertas.service';

@Component({
  selector: 'app-form-tema',
  templateUrl: './form-tema.component.html',
  styleUrls: ['./form-tema.component.css']
})
export class FormTemaComponent implements OnInit {

  key = 'data'
  reverse = true

  tema: Tema = new Tema()
  listaTemas: Tema[]
  id: number
  postagem: Postagem = new Postagem()
  listaPostagens: Postagem[]
  titulo: string
  categoriaAjuda: string


  constructor(
    private temaService: TemaService,
    private router: Router,
    private alert: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    
    if(environment.tipo != 'adm'){
      this.alert.showAlertInfo('Você precisa ser adm para acessar essa rota')
      this.router.navigate(['/home'])
    }


    this.findAllTemas()
  }

 
 

  findAllTemas(){
    this.temaService.getAllTemas().subscribe((resp: Tema[])=>{
      this.listaTemas = resp
    })
  }

  findByIdTema(){
    this.temaService.getByIdTema(this.tema.idTema).subscribe((resp: Tema)=>{
      this.tema = resp
    })
  }

  cadastrar(){
    if(this.tema.categoriaAjuda == null || this.tema.descricao == null){
      this.alert.showAlertDanger("Preencha todos os campos")
    }else{
      this.temaService.postTema(this.tema).subscribe((resp: Tema)=>{
        this.tema = resp
        this.router.navigate(['/painel'])
        this.alert.showAlertSuccess('Tema cadastrado com sucesso')
      })
    }
  }
}
