package com.renanmateus.diazero.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.renanmateus.diazero.model.Incident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore all null fields
public class IncidentResponseDTO {

	private Long idIncident;
	private String name;
	private String description;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime closedAt;

	// A resposta deve ser diferente para níveis de usuários distintos(user, admin)
	// para um user, os objetos do tipo LocalDateTime não devem ser mostrados.
	// foi necessário criar um construtor, para que se possa usar métodos diferentes
	// afim de retornar respostas diferentes para cada tipo de usuário

	public IncidentResponseDTO(Long idIncident, String name, String description, String status) {
		this.idIncident = idIncident;
		this.name = name;
		this.description = description;
		this.status = status;

	}

	public IncidentResponseDTO transformDTOUserRole(Incident incident) {
		return new IncidentResponseDTO(incident.getIdIncident(), incident.getName(), incident.getDescription(), incident.getStatus());

	}

	public IncidentResponseDTO transformDTOAdminRole(Incident incident) {
		return new IncidentResponseDTO(incident.getIdIncident(), incident.getName(), incident.getDescription(), incident.getStatus(),
				incident.getCreatedAt(), incident.getUpdatedAt(), incident.getClosedAt());

	}
}
