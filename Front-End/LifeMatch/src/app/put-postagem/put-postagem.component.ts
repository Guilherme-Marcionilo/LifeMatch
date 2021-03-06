import { MidiaServiceService } from './../midia-service.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';
import { AlertasService } from './../service/alertas.service';

@Component({
  selector: 'app-put-postagem',
  templateUrl: './put-postagem.component.html',
  styleUrls: ['./put-postagem.component.css']
})
export class PutPostagemComponent implements OnInit {

  postagem: Postagem = new Postagem()
  idPostagem: number

  imagem: File

  tema: Tema = new Tema()
  listaTemas: Tema[]
  id: number

  constructor(
    private temaService: TemaService,
    private postagemService: PostagemService,
    private router: Router,
    private route: ActivatedRoute,
    private alert: AlertasService,
    private midiaService: MidiaServiceService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    this.idPostagem = this.route.snapshot.params["id"]
    this.findByIdPostagem(this.idPostagem)

    this.findAllTemas()
  }

  findByIdPostagem(id: number){
    this.postagemService.getByIdPostagem(id).subscribe((resp: Postagem)=>{
      this.postagem = resp
    })
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

  carregarImg(event: any) {
    this.imagem = event.target.files[0]     
  }




  salvar(){

    if (this.imagem) {
      console.log(this.imagem)
      this.midiaService.uploadPhoto(this.imagem).subscribe((resp: any) => {
      
      this.postagem.imagensPostagem = resp.secure_url
      
      this.tema.idTema = this.id
      this.postagem.tema = this.tema
  
      this.postagemService.putPostagem(this.postagem).subscribe((resp: Postagem)=>{
        this.postagem = resp
        this.router.navigate(['/painel'])
        this.alert.showAlertSuccess('Postagem alterada com sucesso')
      }, err => {
        if(err.status == '500'){
          this.alert.showAlertDanger('Preencha todos os campos corretamente antes de enviar!')
        }
      })
    

    })
  }

  }

}
