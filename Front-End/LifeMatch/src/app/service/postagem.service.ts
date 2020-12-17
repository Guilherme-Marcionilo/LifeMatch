import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Postagem } from '../model/Postagem';
import { environment } from './../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  constructor(private http: HttpClient) { }

  //atribuindo a variável token o objeto HttpHeaders para autorizar a consulta.
  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  //Método responsável por retornar as postagens.
  getAllPostagens(): Observable<Postagem[]>{
    return this.http.get<Postagem[]>('http://localhost:8080/postagem', this.token)
  }

  getByIdPostagem(id: number):Observable<Postagem>{
    return this.http.get<Postagem>(`http://localhost:8080/postagem/${id}`, this.token)
  }

  postPostagem(postagem: Postagem):Observable<Postagem>{
    return this.http.post<Postagem>('http://localhost:8080/postagem', postagem, this.token)
  }

  putPostagem(postagem: Postagem):Observable<Postagem>{
    return this.http.put<Postagem>('http://localhost:8080/postagem', postagem, this.token)
  }

  deletePostagem(id: number){
    return this.http.delete<Postagem>(`http://localhost:8080/postagem/${id}`, this.token)
  }

  getByTituloPostagem(titulo: string): Observable<Postagem[]>{
    return this.http.get<Postagem[]>(`http://localhost:8080/postagem/titulo/${titulo}`, this.token) 
  }

}
