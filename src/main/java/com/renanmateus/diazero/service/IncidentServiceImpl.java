package com.renanmateus.diazero.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renanmateus.diazero.exception.IncidentAlredyCloseException;
import com.renanmateus.diazero.exception.IncidentNotFoundException;
import com.renanmateus.diazero.model.Incident;
import com.renanmateus.diazero.repository.IncidentRepository;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@Override
	public Incident save(Incident incident) {
		incident.setCreatedAt(LocalDateTime.now());
		incident.setStatus("created");
		return this.incidentRepository.save(incident);
	}

	@Override
	public Page<Incident> findAll(Pageable pageable) {
		return this.incidentRepository.findAll(pageable);
	}

	@Override
	public Incident findById(Long id) {
		Optional<Incident> incident = this.incidentRepository.findById(id);
		return incident.orElseThrow(() -> new IncidentNotFoundException());
	}

	@Override
	public Incident updateById(Long id, Incident incident) {
		Optional<Incident> newIncident = this.incidentRepository.findById(id);
		if (newIncident.isPresent()) {
			LocalDateTime alredyClosed = this.incidentRepository.findById(id).get().getClosedAt();
			if (alredyClosed == null) {
				Incident savedIncident = copyProperties(id, incident);
			//Incident savedIncident = new Incident();	
			savedIncident.setCreatedAt(newIncident.get().getCreatedAt());
				savedIncident.setUpdatedAt(LocalDateTime.now());
				savedIncident.setStatus("updated");
				return this.incidentRepository.save(savedIncident);
			}
			throw new IncidentAlredyCloseException();
		}
		throw new IncidentNotFoundException();
	}

	@Override
	public Incident closeById(Long id) {
		Optional<Incident> newIncident = this.incidentRepository.findById(id);
		if (newIncident.isPresent()) {
			LocalDateTime alredyClosed = this.incidentRepository.findById(id).get().getClosedAt();
			if (alredyClosed == null) {
				Incident savedIncident = copyProperties(id, newIncident.get());
				savedIncident.setCreatedAt(newIncident.get().getCreatedAt());
				savedIncident.setUpdatedAt(newIncident.get().getUpdatedAt());
				// BeanUtils.copyProperties(newIncident, savedIncident);
				savedIncident.setClosedAt(LocalDateTime.now());
				savedIncident.setStatus("closed");
				return this.incidentRepository.save(savedIncident);
			}
			throw new IncidentAlredyCloseException();
		}
		throw new IncidentNotFoundException();
	}

	@Override
	public void deleteById(Long id) {
		Optional<Incident> incident = this.incidentRepository.findById(id);
		if (incident.isPresent()) {
			this.incidentRepository.deleteById(id);
		} else
			throw new IncidentNotFoundException();
	}

	public Incident copyProperties(Long id, Incident incident) {
		Incident savedIncident = new Incident();
		savedIncident.setName(incident.getName());
		savedIncident.setDescription(incident.getDescription());
		savedIncident.setIdIncident(id);
		return savedIncident;
	}
}
