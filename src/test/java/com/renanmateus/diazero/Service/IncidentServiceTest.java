package com.renanmateus.diazero.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.renanmateus.diazero.model.Incident;
import com.renanmateus.diazero.repository.IncidentRepository;
import com.renanmateus.diazero.service.IncidentServiceImpl;

@SpringBootTest
public class IncidentServiceTest {

	@Autowired
	private IncidentServiceImpl incidentService;

	@MockBean
	private IncidentRepository incidentRepository;

	private Incident incident;

	@BeforeEach
	public void setUp() {
		incident = Incident.builder().idIncident(1L).name("name").description("description")
				.createdAt(LocalDateTime.now()).build();
	}

	@Test
	public void mustReturnIncident_WhenRegisterIncidentSuccessful() {
		when(this.incidentRepository.save(any(Incident.class))).thenReturn(incident);
		Incident incidentTest = this.incidentService.save(incident);
		assertThat(incidentTest).isNotNull();
	}

	@Test
	public void mustReturnIncident_WhenFindIncidentExisting() {
		when(this.incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
		Incident incidentTest = this.incidentService.findById(1L);
		assertThat(incidentTest).isNotNull();
	}

	@Test
	public void mustReturnIncident_WhenUpdateIncidentExisting() {
		when(this.incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
		when(this.incidentRepository.save(any(Incident.class))).thenReturn(incident);
		Incident incidentTest = this.incidentService.updateById(1L, incident);
		assertThat(incidentTest.getName()).isEqualTo(incident.getName());
		assertThat(incidentTest.getDescription()).isEqualTo(incident.getDescription());
	}

	@Test
	public void mustReturnIncident_WhenCloseIncidentExisting() {
		when(this.incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
		when(this.incidentRepository.save(any(Incident.class))).thenReturn(incident);
		Incident incidentTest = this.incidentService.closeById(1L);
		assertThat(incidentTest.getName()).isEqualTo(incident.getName());
		assertThat(incidentTest.getDescription()).isEqualTo(incident.getDescription());
	}

	@Test
	public void mustReturnNothing_WhenDeleteIncidentExisting() {
		when(this.incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
		Assertions.assertDoesNotThrow(() -> incidentService.deleteById(1L));
	}

}