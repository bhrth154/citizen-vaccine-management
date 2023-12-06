package com.example.ctznvacc.MyCitizenVaccineREST.POJO;

import java.sql.Date;

public class RequestPOJO {
	long id;
	String city;
	String vaccine;
	Date date;

	public RequestPOJO() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestPOJO(long id, String city, String vaccine, Date date) {
		this.id = id;
		this.city = city;
		this.vaccine = vaccine;
		this.date = date;
	}

	@Override
	public String toString() {
		return "RequestPOJO [id=" + id + ", city=" + city + ", vaccine=" + vaccine + ", date=" + date + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
