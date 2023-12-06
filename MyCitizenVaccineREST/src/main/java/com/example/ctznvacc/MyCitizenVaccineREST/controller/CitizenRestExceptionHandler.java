package com.example.ctznvacc.MyCitizenVaccineREST.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ctznvacc.MyCitizenVaccineREST.exception.CitizenNotFoundException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.CitizenNotVaccinatedException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.NoCitizensFoundException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.NotNumberException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.UnableToVaccinateException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.VaccineErrorResponse;

@ControllerAdvice
public class CitizenRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<VaccineErrorResponse> handleException(CitizenNotFoundException exp){
		
		VaccineErrorResponse error = new VaccineErrorResponse(HttpStatus.NOT_FOUND.value(),exp.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<VaccineErrorResponse> handleException(CitizenNotVaccinatedException exp){
		
		VaccineErrorResponse error = new VaccineErrorResponse(HttpStatus.BAD_REQUEST.value(),exp.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<VaccineErrorResponse> handleException(NoCitizensFoundException exp){
		
		VaccineErrorResponse error = new VaccineErrorResponse(HttpStatus.NOT_FOUND.value(),exp.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<VaccineErrorResponse> handleException(UnableToVaccinateException exp){
		
		VaccineErrorResponse error = new VaccineErrorResponse(HttpStatus.BAD_REQUEST.value(),exp.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<VaccineErrorResponse> handleException(NotNumberException exp){
		
		VaccineErrorResponse error = new VaccineErrorResponse(HttpStatus.BAD_REQUEST.value(),exp.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
