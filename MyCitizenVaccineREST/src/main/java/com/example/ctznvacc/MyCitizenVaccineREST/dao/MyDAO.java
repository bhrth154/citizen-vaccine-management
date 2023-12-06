package com.example.ctznvacc.MyCitizenVaccineREST.dao;

import java.sql.Date;
import java.util.List;

import com.example.ctznvacc.MyCitizenVaccineREST.entity.Vaccine;

public interface MyDAO {
	
	public void save(Vaccine theVaccine);
	
	public List<Vaccine> findAll();

	public List<Vaccine> findByStatus(String status);
	
	public List<Vaccine> findByStatus();
 	
	public List<Vaccine> findByVaccine();

	public List<Vaccine> findCountByVaccine();
	
	public Vaccine findById(long theId);

	public List<Vaccine> findWhoVaccinated();
	
	public void updateVaccination(long id,String city,String vaccine,Date date);
	
	public void updateDetails(Vaccine theVaccine);
	
	public void deleteFully();
	
	public void deleteById(long theId);
}
