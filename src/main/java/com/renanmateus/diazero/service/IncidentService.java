package com.renanmateus.diazero.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.renanmateus.diazero.model.Incident;

public interface IncidentService {	
	
	// salvar
	Incident save(Incident incident);
	
	// listar
	Page<Incident> findAll(Pageable pageable);	
	
	// buscar
	Incident findById(Long id);
	
	// atualizar
	Incident updateById(Long id, Incident incident);
	
	// fechar
	Incident closeById(Long id);

	// remover
	void deleteById(Long id);
	
	
	
	
}
