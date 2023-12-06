package com.example.ctznvacc.MyCitizenVaccineREST.controller;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	public MyControllerREST(MyService theMyService) {
		this.theMyService = theMyService;
	}
	
	@GetMapping("/citizens")
	public List<Vaccine> findAll() {
		
		if(theMyService.findAll().isEmpty())
		{
			throw new NoCitizensFoundException("Empty list");
		}
		
		return theMyService.findAll();
	}
	
	private boolean checkInt(String id)
	{
		if(id==null)
			return false;
		try {
			long num = Long.parseLong(id);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	@GetMapping("/citizens/{theCitizenId}")
	public Vaccine getById(@PathVariable String theCitizenId) {
		
		boolean b = checkInt(theCitizenId);
		
		if(!b) {
			throw new NotNumberException("CitizenID must be a number");
		}
		
		long citizenId = Long.parseLong(theCitizenId);
		
		Vaccine theVaccine = theMyService.findById(citizenId);

		if (theVaccine == null) {
			throw new CitizenNotFoundException("Employee not found with id : " + citizenId );
		}
		
		if(theVaccine.getCount()==0){
			throw new CitizenNotVaccinatedException("Employee found with id " + citizenId + " is not vaccinated");
		}

		return theVaccine;
	}

	@GetMapping("/citizens/status/{status}")
	public List<Vaccine> findByStatus(@PathVariable String status) {

		if(theMyService.findByStatus(status).isEmpty())
		{
			throw new CitizenNotFoundException("No citizen is " + status);
		}
		
		return theMyService.findByStatus(status);
	}
	
	@GetMapping("/citizens/status")
	public List<Vaccine> findByStatus() {
		
		return theMyService.findByStatus();
	}

	@GetMapping("/vaccinated/citizen")
	public List<Vaccine> findWhoVaccinated() {

		if(theMyService.findWhoVaccinated().isEmpty())
		{
			throw new CitizenNotVaccinatedException("No citizen is vaccinated");
		}
		
		return theMyService.findWhoVaccinated();
	}
	
	@GetMapping("/citizens/vaccine")
	public List<Vaccine> findByVaccine()
	{
		return theMyService.findByVaccine();
	}
	
	@GetMapping("/citizens/count")
	public List<Vaccine> findCountByVaccine()
	{
		return theMyService.findCountByVaccine();
	}

	@PostMapping("/citizens")
	public String addCitizen(@RequestBody Vaccine theVaccine) {
		
		theMyService.save(theVaccine);

		return theVaccine.toString();
	}
	
	@PutMapping("/citizen/update")
	public String updateCitizen(@RequestBody Vaccine theVaccine) {
		
		theMyService.updateDetails(theVaccine);

		return theVaccine.toString();
	}


	public long days(Date d1, Date d2)
	{
		 
		
        long difference_In_Time = (d1.getTime() - d2.getTime());
		long diff_in_years = (difference_In_Time) / (1000l * 60 * 60 * 24 * 365);
		
//		long difference_In_Days= ((difference_In_Time / (1000 * 60 * 60 * 24))% 365) + (diff_in_years * 365);
//		return difference_In_Days;
		
		long diff = TimeUnit.DAYS.convert(difference_In_Time, TimeUnit.MILLISECONDS);
		
		return diff;
	}
	
	@PutMapping("/citizens")
	public String vaccinateDose(@RequestBody RequestPOJO theRequestPOJO) {
		
		long id = theRequestPOJO.getId();
		String city = theRequestPOJO.getCity();
		String vaccine = theRequestPOJO.getVaccine();
		Date date = theRequestPOJO.getDate();
		
		if(date == null)
		{
			long millis=System.currentTimeMillis();  
	        date=new Date(millis);
		}
		
		Vaccine tempVaccine = theMyService.findById(id);
		
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
				if(date.compareTo(tempVaccine.getDate1())==(-1) || date.compareTo(tempVaccine.getDate1())==0)
				{
					throw new UnableToVaccinateException("Unable to vaccinate. Enter a date that comes after " + tempVaccine.getDate1());
				}
				
				Date d = tempVaccine.getDate1();
				long days = days(date,d);
				
				System.out.println("days : " + days);
				
				if(days<120)
					throw new UnableToVaccinateException("you have to wait for " + (120-days) + " days for second dose");
			}
			
			if(tempVaccine.getCount()==2)
			{
				if(date.compareTo(tempVaccine.getDate1())==(-1) || date.compareTo(tempVaccine.getDate1())==0)
				{
					throw new UnableToVaccinateException("Unable to vaccinate. Enter a date that comes after " + tempVaccine.getDate1());
				}
				
				Date d = tempVaccine.getDate2();
				long days = days(date,d);
				
				if(days<270)
					throw new UnableToVaccinateException("you have to wait for " + (270-days) + " days for booster dose");
			}
		}
		
		theMyService.updateVaccination(id,city,vaccine,date);
		
		return theMyService.findById(id).getName() + " is " + theMyService.findById(id).getStatus();
	}

	@DeleteMapping("/citizens")
	public List<Vaccine> deleteCitizen() {
		
		List<Vaccine> theVaccine = theMyService.findByStatus("completed booster");
		

		if(theVaccine.isEmpty())
		{
			throw new NoCitizensFoundException("No citizen is vaccinated fully");
		}
		
		theMyService.deleteFully();
		
		
		return theVaccine;
	}

	@DeleteMapping("/citizens/{citizenId}")
	public String deleteEmployee(@PathVariable long citizenId) {

		Vaccine tempVaccine = theMyService.findById(citizenId);

		if (tempVaccine == null) {
			throw new NoCitizensFoundException("Citizen id not found - " + citizenId);
		}

		if(tempVaccine.getCount()<3) {
			throw new CitizenNotVaccinatedException("Unable to delete employee with id " + citizenId + " as he/she is not fully vaccinated\n");
		}
		
		theMyService.deleteById(citizenId);

		return "Deleted citizen with id : " + citizenId;
	}
}
