package com.renanmateus.diazero.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renanmateus.diazero.dto.IncidentResponseDTO;
import com.renanmateus.diazero.dto.IncidentRequestDTO;
import com.renanmateus.diazero.model.Incident;
import com.renanmateus.diazero.service.IncidentServiceImpl;

@RestController
@RequestMapping(value = "/incidents")
public class IncidentController {
	@Autowired
	private IncidentServiceImpl incidentService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public IncidentResponseDTO save(@RequestBody @Valid IncidentRequestDTO incidentRequestDTO,
			HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			Incident incident = this.incidentService.save(incidentRequestDTO.transformToIncident());
			return new IncidentResponseDTO().transformDTOAdminRole(incident);
		}
		Incident incident = this.incidentService.save(incidentRequestDTO.transformToIncident());
		return new IncidentResponseDTO().transformDTOUserRole(incident);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping
	public Page<IncidentResponseDTO> findAll(Pageable pageable, HttpServletRequest request) {
		Page<Incident> incidents = this.incidentService.findAll(pageable);
		if (request.isUserInRole("ROLE_ADMIN")) {
			Page<IncidentResponseDTO> indicentsResponseDTO = incidents
					.map(incident -> new IncidentResponseDTO().transformDTOAdminRole(incident));
			return indicentsResponseDTO;
		}
		Page<IncidentResponseDTO> indicentsResponseDTO = incidents
				.map(incident -> new IncidentResponseDTO().transformDTOUserRole(incident));
		return indicentsResponseDTO;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/{incidentId}")
	public IncidentResponseDTO findById(@PathVariable Long incidentId, HttpServletRequest request) {
		Incident incident = this.incidentService.findById(incidentId);
		if (request.isUserInRole("ROLE_ADMIN")) {
			return new IncidentResponseDTO().transformDTOAdminRole(incident);
		}
		return new IncidentResponseDTO().transformDTOUserRole(incident);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/update/{incidentId}")
	public IncidentResponseDTO updateById(@PathVariable Long incidentId,
			@RequestBody @Valid IncidentRequestDTO incidentRequestDTO, HttpServletRequest request) {
		Incident incident = this.incidentService.updateById(incidentId, incidentRequestDTO.transformToIncident());
		if (request.isUserInRole("ROLE_ADMIN")) {
			return new IncidentResponseDTO().transformDTOAdminRole(incident);
		}
		return new IncidentResponseDTO().transformDTOUserRole(incident);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/close/{incidentId}")
	public IncidentResponseDTO closeById(@PathVariable Long incidentId, HttpServletRequest request) {
		Incident incident = this.incidentService.closeById(incidentId);
		if (request.isUserInRole("ROLE_ADMIN")) {
			return new IncidentResponseDTO().transformDTOAdminRole(incident);
		}
		return new IncidentResponseDTO().transformDTOUserRole(incident);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{incidentId}")
	public void deleteById(@PathVariable Long incidentId) {
		this.incidentService.deleteById(incidentId);

	}

}
