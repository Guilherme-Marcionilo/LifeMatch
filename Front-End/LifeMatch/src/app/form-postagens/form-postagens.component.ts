import { MidiaServiceService } from './../midia-service.service';
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

  imagem: File
  tema: Tema = new Tema()
  listaTemas: Tema[]
  id: number



  constructor(
    private temaService: TemaService,
    private postagemService: PostagemService,
    private alert: AlertasService,
    private midiaService: MidiaServiceService
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

        // fazer o id do user
        // this.postagem.usuario.id = idUsuario

        if (this.imagem) {
          console.log(this.imagem)
          this.midiaService.uploadPhoto(this.imagem).subscribe((resp: any) => {
          
          this.postagem.imagensPostagem = resp.secure_url
            
          this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
          
            this.postagem = resp
            this.postagem = new Postagem()
  
            this.alert.showAlertSuccess('Postagem realizada com sucesso')
          })

        })
      }
      else {
        // Cadastrar uma imagem Padrão
        console.log(this.imagem)
        this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
          
          this.postagem = resp
          this.postagem = new Postagem()
  
          this.alert.showAlertSuccess('Postagem realizada com sucesso')
        })
      }

     
        
      }
     }

     carregarImg(event: any) {
      this.imagem = event.target.files[0]     
    }


}
