package com.Ampara.LifeMatch.controller;

import java.util.List;
import java.util.Optional;

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

import com.Ampara.LifeMatch.model.UsuarioLogin;
import com.Ampara.LifeMatch.model.UsuarioModel;
import com.Ampara.LifeMatch.repository.UsuarioRepository;
import com.Ampara.LifeMatch.service.UsuarioService;


@RestController
@RequestMapping ("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	//INJETANDO O USUARIOSERVICE ONDE CONTEM AS REGRAS DE NEGOCIO PARA LOGAR OU CADASTRAR UM USUARIO
	@Autowired
	private UsuarioService usuarioService;
	
	//END POINT CRIADO PARA O USUÁRIO SE LOGAR
	//AQUI RECEBEMOS UM OPTIONAL DE USUARIO LOGIN QUE FOI CRIADO ESPECIFICAMENTE PARA FAZER O REQUEST E RESPONSE DO LOGIN
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
		
	//END POINT CRIADO PARA QUE POSSA CADASTRAR UM USUÁRIO
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel usuario){
		Optional<UsuarioModel> user = usuarioService.CadastrarUsuario(usuario);
			try {
				return ResponseEntity.ok(user.get());
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
	}
	
	//MÉTODO GET QUE BUSCA TODOS USUARIOS
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//MÉTODO GET QUE BUSCA O USUARIO PELO ID
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp-> ResponseEntity.ok(resp))
						.orElse(ResponseEntity.notFound().build());
	}
	
	//MÉTODO GET QUE BUSCA O USUARIO PELO LOGIN
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<UsuarioModel> getByfindByUsuario(@PathVariable String usuario){
		return repository.findByUsuarioContainingIgnoreCase(usuario)
				.map(resp-> ResponseEntity.ok(resp))
						.orElse(ResponseEntity.notFound().build());
	}
	
	//MÉTODO POST QUE CRIA UM NOVO USUARIO
	@PostMapping
	public ResponseEntity<UsuarioModel> post(@RequestBody UsuarioModel usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	//MÉTODO PUT QUE ATUALIZA AS INFORMAÇÕES DE UM USUARIO
	@PutMapping
	public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel usuario){
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	//MÉTODO DELETE QUE APAGA UM USUARIO
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
