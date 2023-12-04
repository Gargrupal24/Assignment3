
package in.co.vwits.sms.dao.impl;

import java.util.List;
import java.util.Optional;

import in.co.vwits.sms.dao.EmployeeDao;
import in.co.vwits.sms.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class EmployeeJpaDaoImpl implements EmployeeDao {

	private EntityManagerFactory factory;
	
	public EmployeeJpaDaoImpl(){
		factory= Persistence.createEntityManagerFactory("smsapp");
	}
	@Override
	public int save(Employee e) {
		// TODO Auto-generated method stub
		// s is an entity object in transient stage.
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		em.persist(e); //this inserts records
		tx.commit();
		em.close();
		return 1;
	}

	
	@Override
	public Optional<Employee> findById(int id) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		Employee e = em.find(Employee.class, id); //this fires select query
		tx.commit();
		em.close();
		return Optional.of(e);
	}

	@Override
	public List<Employee> findAll() {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		/* call a method which returns all the records from the database---*/
		String jpql = "FROM Student";
		TypedQuery<Employee> query = em.createQuery(jpql,Employee.class);
		List<Employee> studentList = query.getResultList();
		tx.commit();
		em.close();
		return studentList;
	}
	

	@Override
	public void updateById(int id, double modifiedSalary) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		Employee e= new Employee();
		e.setId(id);
		e.setSalary(modifiedSalary);
		em.merge(e);//this fires update query
		tx.commit();
		em.close();
		
	}
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		em.remove(em.find(Employee.class, id)); //this fires delete querry.
		tx.commit();
		em.close();	
	}
	@Override
	public void updateById(Employee e) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx =em.getTransaction();
		tx.begin();
		em.merge(e);//this fires update query
		tx.commit();
		em.close();
		
		
	}
}
