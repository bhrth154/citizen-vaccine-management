package com.example.ctznvacc.MyCitizenVaccineREST.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.example.ctznvacc.MyCitizenVaccineREST.entity.Vaccine;
	
@Repository
public class MyDAOImpl implements MyDAO {

	
	private EntityManager entityManager;
	
	public MyDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	@Transactional
	public void save(Vaccine theVaccine) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.save(theVaccine);
		currentSession.clear();
	}

	
	@Override
	public List<Vaccine> findAll() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("from Vaccine", Vaccine.class);
		
		List<Vaccine> vaccine = theQuery.getResultList();
		currentSession.clear();
		return vaccine;
	}


	@Override
	public List<Vaccine> findByStatus(String status) {
		// TODO Auto-generated method stub	
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("from Vaccine v WHERE v.status=:stat",Vaccine.class);
		
		theQuery.setParameter("stat", status);
		
		List<Vaccine> vaccines = theQuery.getResultList();
		currentSession.clear();
		return vaccines;
	}


	@Override
	public List<Vaccine> findByStatus() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		String hql = "FROM Vaccine v ORDER BY v.status,v.id";
		
		Query theQuery = currentSession.createQuery(hql, Vaccine.class);
		
		List<Vaccine> result = theQuery.getResultList();
		currentSession.clear();
		return result;
	}


	@Override
	public List<Vaccine> findByVaccine() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		String hql = "FROM Vaccine v ORDER BY v.vaccine,v.id";
		
		Query theQuery = currentSession.createQuery(hql, Vaccine.class);
		
		List<Vaccine> result = theQuery.getResultList();
		currentSession.clear();
		return result;
	}


	@Override
	public List<Vaccine> findCountByVaccine() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		String hql = "SELECT v.vaccine,count(v.name) FROM Vaccine v GROUP BY v.vaccine ORDER BY v.vaccine";		
		
		Query theQuery = currentSession.createQuery(hql);
		
		List<Vaccine> result = theQuery.getResultList();
		currentSession.clear();
		return result;
	}


	@Override
	public Vaccine findById(long theId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Vaccine vaccine = currentSession.get(Vaccine.class, theId);
		currentSession.clear();
		return vaccine;
	}


	@Override
	public List<Vaccine> findWhoVaccinated() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("from Vaccine v WHERE v.count>:v",Vaccine.class);
		
		theQuery.setParameter("v",0);
		
		List<Vaccine> vaccines = theQuery.getResultList();
		currentSession.clear();
		return vaccines;
	}


	@Override
	@Transactional
	public void updateVaccination(long id, String city, String vaccine,Date date) {
		// TODO Auto-generated method stub

		Session currentSession = entityManager.unwrap(Session.class);
		
		Vaccine theVaccine = findById(id);
        
        if(theVaccine.getCount()==0)
        {
        	theVaccine.setStatus("vaccinated partially");
        	theVaccine.setCount(1);
        	theVaccine.setVaccine(vaccine);
        	theVaccine.setCity_one(city);
        	theVaccine.setDate_one(date);
        }
        else if(theVaccine.getCount()==1)
        {
        	theVaccine.setStatus("vaccinated fully");
        	theVaccine.setCount(2);
        	theVaccine.setCity_two(city);
        	theVaccine.setDate_two(date);
        }
        else if(theVaccine.getCount()==2)
        {
        	theVaccine.setStatus("completed booster");
        	theVaccine.setCount(3);
        	theVaccine.setCity_three(city);
        	theVaccine.setBooster(date);
        }
        
        currentSession.update(theVaccine);
		currentSession.clear();
	}


	@Override
	@Transactional
	public void updateDetails(Vaccine theVaccine) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(theVaccine);
		currentSession.clear();
	}


	@Override
	@Transactional
	public void deleteFully() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Vaccine v WHERE v.count=:c");
		
		int c = 3;
		  
		theQuery.setParameter("c", c); 
		
		theQuery.executeUpdate();

		currentSession.clear();
	}


	@Override
	@Transactional
	public void deleteById(long theId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Vaccine v where v.id=:vid and v.count=:c");
		
		theQuery.setParameter("vid",theId);

		theQuery.setParameter("c",3);
		
		theQuery.executeUpdate();

		currentSession.clear();
	}

}
