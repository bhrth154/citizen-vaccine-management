package com.example.ctznvacc.MyCitizenVaccineREST.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ctznvacc.MyCitizenVaccineREST.dao.MyDAO;
import com.example.ctznvacc.MyCitizenVaccineREST.entity.Vaccine;

@Service
public class MyServiceImpl implements MyService {

	private MyDAO theMyDAO;
	
	
	@Autowired
	public MyServiceImpl(MyDAO theMyDAO) {
		this.theMyDAO = theMyDAO;
	}

	@Override
	@Transactional
	public void save(Vaccine theVaccine) {
		// TODO Auto-generated method stub
		theMyDAO.save(theVaccine);
	}
	
	@Override
	@Transactional
	public List<Vaccine> findAll() {
		// TODO Auto-generated method stub
		return theMyDAO.findAll();
	}

	@Override
	@Transactional
	public List<Vaccine> findByStatus(String status) {
		// TODO Auto-generated method stub
		return theMyDAO.findByStatus(status);
	}

	@Override
	@Transactional
	public List<Vaccine> findByStatus() {
		// TODO Auto-generated method stub
		return theMyDAO.findByStatus();
	}

	@Override
	@Transactional
	public List<Vaccine> findByVaccine() {
		// TODO Auto-generated method stub
		return theMyDAO.findByVaccine();
	}

	@Override
	@Transactional
	public List<Vaccine> findCountByVaccine() {
		// TODO Auto-generated method stub
		return theMyDAO.findCountByVaccine();
	}

	@Override
	@Transactional
	public Vaccine findById(long theId) {
		// TODO Auto-generated method stub
		return theMyDAO.findById(theId);
	}

	@Override
	@Transactional
	public List<Vaccine> findWhoVaccinated() {
		// TODO Auto-generated method stub
		return theMyDAO.findWhoVaccinated();
	}

	@Override
	@Transactional
	public void updateVaccination(long id, String city, String vaccine,Date date) {
		// TODO Auto-generated method stub
		theMyDAO.updateVaccination(id, city, vaccine,date);
	}

	@Override
	@Transactional
	public void updateDetails(Vaccine theVaccine) {
		// TODO Auto-generated method stub
		theMyDAO.updateDetails(theVaccine);
	}

	@Override
	@Transactional
	public void deleteFully() {
		// TODO Auto-generated method stub
		theMyDAO.deleteFully();
	}

	@Override
	@Transactional
	public void deleteById(long theId) {
		// TODO Auto-generated method stub
		theMyDAO.deleteById(theId);
	}

}
