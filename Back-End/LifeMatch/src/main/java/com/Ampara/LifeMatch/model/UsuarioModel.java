package com.Ampara.LifeMatch.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table (name = "tb_usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioModel {
	
	//ATRIBUTOS 
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column
	@NotNull
	@Size (min = 4,max = 50)
	private String nome;
	
	@Column
	@NotNull
	private String senha;
	
	@Column
	@NotNull
	private String categoriaUsuario;
	
	@Column
	@NotNull
	@Size (min = 9,max = 50)
	private String email;

	@Column
	@NotNull
	private String imagem;
	
	@Column
	@NotNull
	private String usuario;
	
//	FAZENDO O TESTE DE ADM OU NORMAL
	@Column
	@NotNull
	private String tipo;
	
	//RELACIONAMENTO ENTRE A TABELA POSTAGEM
	@OneToMany (mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private  List<PostagemModel> postagem;

	
	//MÉTODOS GETTERS AND SETTERS
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCategoriaUsuario() {
		return categoriaUsuario;
	}

	public void setCategoriaUsuario(String categoriaUsuario) {
		this.categoriaUsuario = categoriaUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	public List<PostagemModel> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<PostagemModel> postagem) {
		this.postagem = postagem;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	
}
