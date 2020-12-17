import { Tema } from './Tema'
import { Usuario } from './Usuario'

export class Postagem {
    public idPostagem: number
    public dataPostagem: Date
    public dataConclusao: Date
    public titulo: string
    public descricao: string
    public identificacao: string
    public imagensPostagem: string
    public usuario: Usuario
    public tema: Tema
}