package com.example.ctznvacc.MyCitizenVaccineREST.service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import com.example.ctznvacc.MyCitizenVaccineREST.POJO.RequestPOJO;
import com.example.ctznvacc.MyCitizenVaccineREST.exception.*;
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
	public void save(Vaccine theVaccine) {
		// TODO Auto-generated method stub
		theMyDAO.save(theVaccine);
	}

	@Override
	public Vaccine findById(String theId) {
		long citizenId;

		try {
			citizenId = Long.parseLong(theId);
		} catch (NumberFormatException e) {
			throw new NotNumberException("CitizenID must be a number");
		}

		Vaccine theVaccine = theMyDAO.findById(citizenId);

		if (theVaccine == null) {
			throw new CitizenNotFoundException("Employee not found with id : " + citizenId );
		}

		if(theVaccine.getCount()==0){
			throw new CitizenNotVaccinatedException("Employee found with id " + citizenId + " is not vaccinated");
		}

		// TODO Auto-generated method stub
		return theVaccine;
	}

	@Override
	public List<Vaccine> findByStatus(String status) {
		// TODO Auto-generated method stub
		List<Vaccine> byStatus = theMyDAO.findByStatus(status);

		if(byStatus.isEmpty())
		{
			throw new CitizenNotFoundException("No citizen is " + status);
		}
		return byStatus;
	}

	@Override
	public List<Vaccine> findAll() {
		// TODO Auto-generated method stub
		List<Vaccine> all = theMyDAO.findAll();
		if(all.isEmpty())
		{
			throw new NoCitizensFoundException("Empty list");
		}
		return all;
	}

	@Override
	public List<Vaccine> findByStatus() {
		// TODO Auto-generated method stub
		List<Vaccine> byStatus = theMyDAO.findByStatus();
		if (byStatus.isEmpty()) {
			throw new CitizenNotFoundException("Empty list");
		}
		return byStatus;
	}

	@Override
	@Transactional
	public List<Vaccine> findByVaccine() {
		// TODO Auto-generated method stub
		List<Vaccine> byVaccine = theMyDAO.findByVaccine();
		if (byVaccine.isEmpty()) {
			throw new CitizenNotFoundException("Empty list");
		}
		return byVaccine;
	}

	@Override
	public List<Vaccine> findCountByVaccine() {
		// TODO Auto-generated method stub
		List<Vaccine> countByVaccine = theMyDAO.findCountByVaccine();
		if (countByVaccine.isEmpty()) {
			throw new CitizenNotFoundException("Empty list");
		}
		return countByVaccine;
	}

	@Override
	public List<Vaccine> findWhoVaccinated() {
		// TODO Auto-generated method stub
		List<Vaccine> whoVaccinated = theMyDAO.findWhoVaccinated();
		if(whoVaccinated.isEmpty())
		{
			throw new CitizenNotVaccinatedException("No citizen is vaccinated");
		}
		return theMyDAO.findWhoVaccinated();
	}

	@Override
	public void updateVaccination(RequestPOJO theRequestPOJO) {

		long id = theRequestPOJO.getId();
		String city = theRequestPOJO.getCity();
		String vaccine = theRequestPOJO.getVaccine();
		Date date = theRequestPOJO.getDate();

		if(date == null)
		{
			long millis=System.currentTimeMillis();
			date=new Date(millis);
		}

		Vaccine tempVaccine = theMyDAO.findById(id);

		if(tempVaccine==null)
		{
			throw new CitizenNotFoundException("Employee not found with id : " + id );
		}

		if(tempVaccine.getCount()==3)
		{
			throw new UnableToVaccinateException("Unable to vaccinate. " + tempVaccine.getName() + " is " + tempVaccine.getStatus() + " on " + tempVaccine.getBooster());
		}

		if(tempVaccine.getCount()>0)
		{

			if(vaccine.compareTo(tempVaccine.getVaccine())!=0)
			{
				throw new UnableToVaccinateException("Unable to vaccinate. " + tempVaccine.getName() + " is vaccinated with " + tempVaccine.getVaccine());
			}

			if(tempVaccine.getCount()==1)
			{
				if(date.compareTo(tempVaccine.getDate_one())==(-1) || date.compareTo(tempVaccine.getDate_one())==0)
				{
					throw new UnableToVaccinateException("Unable to vaccinate. Enter a date that comes after " + tempVaccine.getDate_one());
				}

				Date d = tempVaccine.getDate_one();
				long days = days(date,d);

				System.out.println("days : " + days);

				if(days<120)
					throw new UnableToVaccinateException("you have to wait for " + (120-days) + " days for second dose");
			}

			if(tempVaccine.getCount()==2)
			{
				if(date.compareTo(tempVaccine.getDate_one())==(-1) || date.compareTo(tempVaccine.getDate_one())==0)
				{
					throw new UnableToVaccinateException("Unable to vaccinate. Enter a date that comes after " + tempVaccine.getDate_one());
				}

				Date d = tempVaccine.getDate_two();
				long days = days(date,d);

				if(days<270)
					throw new UnableToVaccinateException("you have to wait for " + (270-days) + " days for booster dose");
			}
		}

		// TODO Auto-generated method stub
		theMyDAO.updateVaccination(id, city, vaccine,date);
	}

	@Override
	public void updateDetails(Vaccine theVaccine) {
		// TODO Auto-generated method stub
		theVaccine.setVaccineDetails(theMyDAO.findById(theVaccine.getId()));
		theMyDAO.updateDetails(theVaccine);
	}

	@Override
	public List<Vaccine> deleteFully() {
		// TODO Auto-generated method stub
		List<Vaccine> theVaccine = theMyDAO.findByStatus("completed booster");


		if(theVaccine.isEmpty())
		{
			throw new NoCitizensFoundException("No citizen is vaccinated fully");
		}

		theMyDAO.deleteFully();


		return theVaccine;
	}

	@Override
	public Vaccine deleteById(long theId) {
		// TODO Auto-generated method stub
		Vaccine tempVaccine = theMyDAO.findById(theId);

		if (tempVaccine == null) {
			throw new NoCitizensFoundException("Citizen id not found - " + theId);
		}

		if(tempVaccine.getCount()<3) {
			throw new CitizenNotVaccinatedException("Unable to delete employee with id " + theId + " as he/she is not fully vaccinated\n");
		}

		theMyDAO.deleteById(theId);

		return tempVaccine;
	}

	public long days(Date d1, Date d2)
	{
		long difference_In_Time = (d1.getTime() - d2.getTime());
		long diff_in_years = (difference_In_Time) / (1000l * 60 * 60 * 24 * 365);

		long diff = TimeUnit.DAYS.convert(difference_In_Time, TimeUnit.MILLISECONDS);

		return diff;
	}

}
