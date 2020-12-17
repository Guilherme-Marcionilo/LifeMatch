import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tema } from '../model/Tema';
import { TemaService } from '../service/tema.service';
import { AlertasService } from './../service/alertas.service';

@Component({
  selector: 'app-delete-tema',
  templateUrl: './delete-tema.component.html',
  styleUrls: ['./delete-tema.component.css']
})
export class DeleteTemaComponent implements OnInit {

  tema: Tema = new Tema()
  id: number
  idTema: number

  constructor(
    private temaService: TemaService,
    private router: Router,
    private route: ActivatedRoute,
    private alert: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    this.idTema = this.route.snapshot.params["id"]
    this.findByIdTema(this.idTema)
  }

  findByIdTema(id: number){
    this.temaService.getByIdTema(id).subscribe((resp: Tema)=>{
      this.tema = resp
    })
  }

  btnSim(){
    this.temaService.deleteTema(this.tema.idTema).subscribe(()=>{
      this.router.navigate(['/form-tema'])
      this.alert.showAlertSuccess('Tema apagado com sucesso')
    })
  }

  btnNao(){
    this.router.navigate(['/form-tema'])
  }

}
