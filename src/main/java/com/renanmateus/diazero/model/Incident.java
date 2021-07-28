package com.renanmateus.diazero.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Incidents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incident {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIncident;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = true)
	private LocalDateTime updatedAt;
	@Column(nullable = true)
	private LocalDateTime closedAt;
	

}
