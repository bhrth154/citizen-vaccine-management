package com.example.ctznvacc.MyCitizenVaccineREST.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="citizen")
@SecondaryTable(name ="vaccine", pkJoinColumns = @PrimaryKeyJoinColumn(name ="id"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="aadhaar",unique=true)
	private long aadhaar;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="age")
	private int age;
	
	@Column(name="phone")
	private long phone;
	
	@Column(name="status")
	private String status;
	
	@Column(name="count",table="vaccine")
	private int count;
	
	@Column(name="vaccine",table="vaccine")
	private String vaccine;
	
	@Column(name="dose_one",table="vaccine")
	private Date date_one;
	
	@Column(name="dose_two",table="vaccine")
	private Date date_two;
	
	@Column(name="booster",table="vaccine")
	private Date booster;
	
	@Column(name="city_one",table="vaccine")
	private String city_one;
	
	@Column(name="city_two",table="vaccine")
	private String city_two;
	
	@Column(name="city_three",table="vaccine")
	private String city_three;

	public void setVaccineDetails(Vaccine vaccine) {
		this.setCity_one(vaccine.getCity_one());
		this.setCity_two(vaccine.getCity_two());
		this.setCity_three(vaccine.getCity_three());
		this.setDate_one(vaccine.getDate_one());
		this.setDate_two(vaccine.getDate_two());
		this.setBooster(vaccine.getBooster());
		this.setCount(vaccine.getCount());
		this.setStatus(vaccine.getStatus());
		this.setVaccine(vaccine.getVaccine());
	}
}
