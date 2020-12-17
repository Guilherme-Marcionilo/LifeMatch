import { Component, OnInit } from '@angular/core';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';
import { AlertasService } from './../service/alertas.service';

@Component({
  selector: 'app-form-postagens',
  templateUrl: './form-postagens.component.html',
  styleUrls: ['./form-postagens.component.css']
})
export class FormPostagensComponent implements OnInit {

  postagem: Postagem = new Postagem()

  tema: Tema = new Tema()
  listaTemas: Tema[]
  id: number

  constructor(
    private temaService: TemaService,
    private postagemService: PostagemService,
    private alert: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0,0)

    this.findAllTemas()
  }

    //criando um método para encontrar todos os Temas e retornando em lista
    findAllTemas(){
      this.temaService.getAllTemas().subscribe((resp: Tema[])=>{
        this.listaTemas = resp
      })
    }

    findByIdTema(){
      this.temaService.getByIdTema(this.id).subscribe((resp: Tema)=>{
        this.tema = resp
      })
    }

    publicar(){
      this.tema.idTema = this.id //O id do meu objeto tema, vai receber id selecionado na postagem
      this.postagem.tema = this.tema
      
      if(this.postagem.titulo == null || this.postagem.descricao == null || this.postagem.tema == null){
        this.alert.showAlertDanger('Preencha todos os campos antes de publicar')
      } else {
        this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
          this.postagem = resp
          this.postagem = new Postagem()
          this.alert.showAlertSuccess('Postagem realizada com sucesso')
        })
      }
     }

}
