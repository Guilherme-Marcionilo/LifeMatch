package com.Ampara.LifeMatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ampara.LifeMatch.model.PostagemModel;
import com.Ampara.LifeMatch.repository.PostagemRepository;

@RestController
@RequestMapping ("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	//MÉTODO GET QUE BUSCA TODAS POSTAGENS
	@GetMapping
	public ResponseEntity<List<PostagemModel>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//MÉTODO GET QUE BUSCA POSTAGENS PELO ID
	@GetMapping("/{id}")
	public ResponseEntity<PostagemModel> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp-> ResponseEntity.ok(resp))
						.orElse(ResponseEntity.notFound().build());
	}
	
	//MÉTODO GET QUE BUSCA POSTAGEM PELO TITULO
	//COM O RESPONSE ENTITY, EM UM TITULO DE POSTAGEM QUE É "BUSCO AUXILIO COM ALIMENTOS"
	//ELE SÓ PUXA QUANDO EU BUSCO PELA PRIMEIRA PALAVRA DO TITULO
	//SE EU TENTO BUSCA PELA PALAVRA "AUXILIO" POR EXEMPLO, ELE DA ERRO
	//JÁ COM O LIST NÃO ACONTECE ISSO, PORÉM TEM A QUESTÃO DE RETORNAR 
	//NULO E NÃO "OK" OU "NOT FOUND" IGUAL AO RESPONSEENTITY
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<PostagemModel>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//MÉTODO POST QUE CRIA UMA POSTAGEM NOVA
	@PostMapping
	public ResponseEntity<PostagemModel> post(@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	//MÉTODO PUT QUE ATUALIZA INFORMAÇÕES DE UMA POSTAGEM
	@PutMapping
	public ResponseEntity<PostagemModel> put(@RequestBody PostagemModel postagem){
		return ResponseEntity.ok(repository.save(postagem));
	}
	
	//MÉTODO DELETE QUE APAGA UMA POSTAGEM BUSCANDO PELO ID
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
