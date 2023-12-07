package com.example.ctznvacc.MyCitizenVaccineREST.controller;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ctznvacc.MyCitizenVaccineREST.POJO.RequestPOJO;
import com.example.ctznvacc.MyCitizenVaccineREST.entity.Vaccine;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.CitizenNotFoundException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.CitizenNotVaccinatedException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.NoCitizensFoundException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.NotNumberException;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.UnableToVaccinateException;
import com.example.ctznvacc.MyCitizenVaccineREST.service.MyService;

@RestController
@RequestMapping("/vaccine/api")
public class MyControllerREST {
	private MyService theMyService;

	@Autowired
	public MyControllerREST(MyService theMyService) {
		this.theMyService = theMyService;
	}
	
	@GetMapping("/citizens")
	public List<Vaccine> findAll() {
		return theMyService.findAll();
	}

	@GetMapping("/citizens/{theCitizenId}")
	public Vaccine getById(@PathVariable String theCitizenId) {
		return theMyService.findById(theCitizenId);
	}

	@GetMapping("/citizens/status/{status}")
	public List<Vaccine> findByStatus(@PathVariable String status) {
		return theMyService.findByStatus(status);
	}
	
	@GetMapping("/citizens/status")
	public List<Vaccine> findByStatus() {
		return theMyService.findByStatus();
	}

	@GetMapping("/vaccinated/citizen")
	public List<Vaccine> findWhoVaccinated() {
		return theMyService.findWhoVaccinated();
	}
	
	@GetMapping("/citizens/vaccine")
	public List<Vaccine> findByVaccine()
	{
		return theMyService.findByVaccine();
	}
	
	@GetMapping("/citizens/count")
	public List<Vaccine> findCountByVaccine() {
		return theMyService.findCountByVaccine();
	}

	@PostMapping("/citizens/")
	public Vaccine addCitizen(@RequestBody Vaccine theVaccine) {
		theMyService.save(theVaccine);
		return theVaccine;
	}
	
	@PutMapping("/citizens/update")
	public Vaccine updateCitizen(@RequestBody Vaccine theVaccine) {
		theMyService.updateDetails(theVaccine);
		return theVaccine;
	}
	
	@PutMapping("/citizens")
	public Vaccine vaccinateDose(@RequestBody RequestPOJO theRequestPOJO) {
		theMyService.updateVaccination(theRequestPOJO);
		return theMyService.findById(String.valueOf(theRequestPOJO.getId()));
	}

	@DeleteMapping("/citizens")
	public List<Vaccine> deleteCitizen() {
		return theMyService.deleteFully();
	}

	@DeleteMapping("/citizens/{citizenId}")
	public Vaccine deleteEmployee(@PathVariable long citizenId) {
		return theMyService.deleteById(citizenId);
	}
}
