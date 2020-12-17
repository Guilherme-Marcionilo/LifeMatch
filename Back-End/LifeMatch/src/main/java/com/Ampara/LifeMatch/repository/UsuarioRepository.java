package com.Ampara.LifeMatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ampara.LifeMatch.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	//MÉTODO PARA SER USADO NO CONTROLLER ONDE BUSCA USUARIO POR LOGIN
	Optional<UsuarioModel> findByUsuarioContainingIgnoreCase(String usuario);

}
