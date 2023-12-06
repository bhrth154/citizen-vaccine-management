package com.example.ctznvacc.MyCitizenVaccineREST.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="ctzn")
@SecondaryTable(name ="vacc", pkJoinColumns = @PrimaryKeyJoinColumn(name ="id"))
public class Vaccine {
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="adhar",unique=true)
	private long adhar;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="age")
	private int age;
	
	@Column(name="phone")
	private long phone;
	
	@Column(name="status")
	private String status;
	
	@Column(name="count",table="vacc")
	private int count;
	
	@Column(name="vaccine",table="vacc")
	private String vaccine;
	
	@Column(name="dose1",table="vacc")
	private Date date1;
	
	@Column(name="dose2",table="vacc")
	private Date date2;
	
	@Column(name="booster",table="vacc")
	private Date booster;
	
	@Column(name="city1",table="vacc")
	private String city1;
	
	@Column(name="city2",table="vacc")
	private String city2;
	
	@Column(name="city3",table="vacc")
	private String city3;

	public Vaccine() {
		// TODO Auto-generated constructor stub
	}

	public Vaccine(long id, String name, long adhar, String gender, int age, long phone, int count, String status,
			String vaccine, Date date1, Date date2, Date booster, String city1, String city2, String city3) {
		this.id = id;
		this.name = name;
		this.adhar = adhar;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.count = count;
		this.status = status;
		this.vaccine = vaccine;
		this.date1 = date1;
		this.date2 = date2;
		this.booster = booster;
		this.city1 = city1;
		this.city2 = city2;
		this.city3 = city3;
	}

	@Override
	public String toString() {
		return "Vaccine [id=" + id + ", name=" + name + ", adhar=" + adhar + ", gender=" + gender + ", age=" + age
				+ ", phone=" + phone + ", count=" + count + ", status=" + status + ", vaccine=" + vaccine + ", date1="
				+ date1 + ", date2=" + date2 + ", booster=" + booster + ", city1=" + city1 + ", city2=" + city2
				+ ", city3=" + city3 + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAdhar() {
		return adhar;
	}

	public void setAdhar(long adhar) {
		this.adhar = adhar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getBooster() {
		return booster;
	}

	public void setBooster(Date booster) {
		this.booster = booster;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getCity3() {
		return city3;
	}

	public void setCity3(String city3) {
		this.city3 = city3;
	}
	
	
}
