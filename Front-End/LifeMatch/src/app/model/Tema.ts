import { Postagem } from './Postagem'

export class Tema {
    public idTema: number
    public categoriaAjuda: string
    public descricao: string
    public postagem: Postagem[] //PostagemModel

}