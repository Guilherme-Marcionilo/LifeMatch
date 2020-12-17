import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tema } from '../model/Tema';
import { Postagem } from './../model/Postagem';
import { environment } from './../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class TemaService {

  constructor(private http: HttpClient) { }

  //atribuindo a variável token o objeto HttpHeaders para autorizar a consulta.
  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  //Método responsável por retornar as temas.
  getAllTemas():Observable<Tema[]>{
    return this.http.get<Tema[]>('http://localhost:8080/tema', this.token)
  }

  getByIdTema(id: number): Observable<Tema> {
    return this.http.get<Tema>(`http://localhost:8080/tema/${id}`, this.token)
  }

  postTema(tema: Tema): Observable<Tema>{
    return this.http.post<Tema>('http://localhost:8080/tema', tema, this.token)
  }

  putTema(tema: Tema): Observable<Tema>{
    return this.http.put<Tema>('http://localhost:8080/tema', tema, this.token)
  }

  deleteTema(id: number): Observable<Tema> {
    return this.http.delete<Tema>(`http://localhost:8080/tema/${id}`, this.token)
  }

  getByNomeTema(categoriaAjuda: string): Observable<Tema[]>{
    return this.http.get<Tema[]>(`http://localhost:8080/tema/categoriaAjuda/${categoriaAjuda}`, this.token) 
  }
}
