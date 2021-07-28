package com.renanmateus.diazero.exception;

public class IncidentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncidentNotFoundException() {
		super("Incident not found");
	}

}
