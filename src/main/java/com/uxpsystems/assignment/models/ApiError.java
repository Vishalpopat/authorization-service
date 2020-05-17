package com.uxpsystems.assignment.models;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
	 
    private HttpStatus status;
    private String message;
    private String logRef;
    private String errorCode;
    private List<String> errors;
 
}

