import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  //atribuindo a variável token o objeto HttpHeaders para autorizar a consulta.
  token = {
    headers: new HttpHeaders().set('Authorization', localStorage.getItem('token')!)
  }

  //Método responsável por retornar as temas.
  getAllUsuario():Observable<Usuario[]>{
    return this.http.get<Usuario[]>('http://localhost:8080/tema', this.token)
  }

  getByIdUsuario(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`http://localhost:8080/tema/${id}`, this.token)
  }

  postUsuario(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>('http://localhost:8080/tema', usuario, this.token)
  }

  putUsuario(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>('http://localhost:8080/tema', usuario, this.token)
  }

  deleteUsuario(id: number): Observable<Usuario> {
    return this.http.delete<Usuario>(`http://localhost:8080/tema/${id}`, this.token)
  }
}
