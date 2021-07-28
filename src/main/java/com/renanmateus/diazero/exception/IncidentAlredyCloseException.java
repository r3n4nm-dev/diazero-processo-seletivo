package com.renanmateus.diazero.exception;

public class IncidentAlredyCloseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IncidentAlredyCloseException() {
		super("Incident alredy close");
	}

}
