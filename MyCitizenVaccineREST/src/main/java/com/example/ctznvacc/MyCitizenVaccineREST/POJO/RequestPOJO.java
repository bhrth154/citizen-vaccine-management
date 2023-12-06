package com.example.ctznvacc.MyCitizenVaccineREST.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPOJO {
	long id;
	String city;
	String vaccine;
	Date date;
}
