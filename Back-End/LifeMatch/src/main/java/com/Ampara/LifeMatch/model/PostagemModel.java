package com.Ampara.LifeMatch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table (name = "tb_postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemModel {
	
	//ATRIBUTOS
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPostagem;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dataPostagem = new java.sql.Date(System.currentTimeMillis());
	
	//Verificar se a data de conclusão será atribuida automaticamente ou não.
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dataConclusao = new java.sql.Date(System.currentTimeMillis());
	
	@Column
	@NotNull
	@Size (min = 4 ,max = 100)
	private String titulo;
	
	@Column
	@NotNull
	@Size (min = 10 ,max = 500)
	private String descricao;
	
	@Column
	private String identificacao;
	
	@Column
	@Size (min = 5 ,max = 9999)
	private String imagensPostagem;
	
	
	//RELACIONAMENTO COM A TABELA POSTAGEM
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private TemaModel tema;
	
	//RELACIONAMENTO COM A TABELA USUARIO
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private UsuarioModel usuario;

	
	//MÉTODOS GETTES AND SETTERS
	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getImagensPostagem() {
		return imagensPostagem;
	}

	public void setImagensPostagem(String imagensPostagem) {
		this.imagensPostagem = imagensPostagem;
	}

	public TemaModel getTema() {
		return tema;
	}

	public void setTema(TemaModel tema) {
		this.tema = tema;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	
	
}
