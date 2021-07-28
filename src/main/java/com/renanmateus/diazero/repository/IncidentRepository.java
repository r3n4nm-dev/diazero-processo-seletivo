package com.renanmateus.diazero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renanmateus.diazero.model.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	
}
