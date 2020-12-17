package com.Ampara.LifeMatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ampara.LifeMatch.model.TemaModel;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{

	//MÉTODO PARA SER USADO NO CONTROLLER ONDE BUSCA TEMA POR CATEGORIA DE AJUDA
	public List<TemaModel> findByCategoriaAjudaContainingIgnoreCase( String categoriaAjuda);
}
