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

import com.Ampara.LifeMatch.model.TemaModel;
import com.Ampara.LifeMatch.repository.TemaRepository;

@RestController
@RequestMapping ("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	//MÉTODO GET QUE BUSCA TODOS OS TEMAS DE POSTAGENS
	@GetMapping
	public ResponseEntity<List<TemaModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//MÉTODO GET QUE BUSCA TEMA DE POSTAGENS PELO ID
	@GetMapping("/{id}")
	public ResponseEntity<TemaModel> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp-> ResponseEntity.ok(resp))
						.orElse(ResponseEntity.notFound().build());
	}
	
	//MÉTODO GET QUE BUSCA TEMA PELA CATEGORIA DE AJUDA
	//@GetMapping("/categoriaAjuda/{categoriaAjuda}")
	//public ResponseEntity<List<TemaModel>> getByCategoriaAjuda(@PathVariable String categoriaAjuda){
	//	return ResponseEntity.ok(repository.findByCategoriaAjudaContainingIgnoreCase(categoriaAjuda));
	//}
	
	@GetMapping("/categoriaAjuda/{categoriaAjuda}")
	public ResponseEntity<List<TemaModel>> getByCategoriaAjuda(@PathVariable String categoriaAjuda){
		return ResponseEntity.ok(repository.findByCategoriaAjudaContainingIgnoreCase(categoriaAjuda));
	}
	
	
	//MÉTODO POST QUE CRIA UM NOVO TEMA
	@PostMapping
	public ResponseEntity<TemaModel> post(@RequestBody TemaModel tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	//MÉTODO PUT QUE ATUALIZA UM TEMA
	@PutMapping
	public ResponseEntity<TemaModel> put(@RequestBody TemaModel tema){
		return ResponseEntity.ok(repository.save(tema));
	}
	
	//MÉTODO DELETE QUE APAGA UM TEMA PELO ID
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
