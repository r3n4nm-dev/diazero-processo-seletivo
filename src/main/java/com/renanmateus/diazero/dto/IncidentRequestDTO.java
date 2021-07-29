package com.renanmateus.diazero.dto;

import javax.validation.constraints.NotBlank;

import com.renanmateus.diazero.model.Incident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentRequestDTO {

	@NotBlank(message="Is required.")
	private String name;
	@NotBlank(message="Is required.")
	private String description;

	public Incident transformToIncident() {
	return new Incident(name, description);
	
}
}
