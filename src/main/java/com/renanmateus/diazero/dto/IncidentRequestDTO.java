package com.renanmateus.diazero.dto;

import com.renanmateus.diazero.model.Incident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentRequestDTO {

	private String name;
	private String description;

	public Incident transformToIncident() {
	return new Incident(name, description);
	
}
}
