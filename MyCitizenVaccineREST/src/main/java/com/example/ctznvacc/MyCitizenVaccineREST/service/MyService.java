package com.example.ctznvacc.MyCitizenVaccineREST.service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import com.example.ctznvacc.MyCitizenVaccineREST.POJO.RequestPOJO;
import com.example.ctznvacc.MyCitizenVaccineREST.entity.Vaccine;

public interface MyService {

	public void save(Vaccine theVaccine);

	public List<Vaccine> findAll();

	public Vaccine findById(String theId);

	public List<Vaccine> findByStatus(String status);

	public List<Vaccine> findByStatus();

	public List<Vaccine> findWhoVaccinated();

	public List<Vaccine> findByVaccine();

	public List<Vaccine> findCountByVaccine();

	public void updateVaccination(RequestPOJO requestPOJO);
	
	public void updateDetails(Vaccine theVaccine);
	
	public List<Vaccine> deleteFully();
	
	public Vaccine deleteById(long theId);
}
