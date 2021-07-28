package com.renanmateus.diazero.exception.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL) // ignore all null fields
@Data

@NoArgsConstructor

public class Error {

	private String message;
	private Integer status;
	private LocalDateTime timestamp;

	public Error(String message, Integer status, LocalDateTime timestamp) {
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}
}
