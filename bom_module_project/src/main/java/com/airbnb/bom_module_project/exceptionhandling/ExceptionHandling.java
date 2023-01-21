package com.airbnb.bom_module_project.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.airbnb.bom_module_project.exception.IdNotFoundException;
import com.airbnb.bom_module_project.respose.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(IdNotFoundException e){
		ResponseStructure<String> responseStructure= new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Id not found");
		responseStructure.setData(e.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity= new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		return responseEntity;
	}
}
