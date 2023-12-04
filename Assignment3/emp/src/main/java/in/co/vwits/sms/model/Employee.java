package in.co.vwits.sms.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name="tbl_employee")
@NamedQuery(name="findAllWithSubjects",query="from Student AS s LEFT JOIN FETCH s.subjectsLearning")
public class Employee implements Comparable<Employee>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  //this will automatically increment the value of rollNo using DB
	private int id;
	@Column(name="st_name",nullable=false)
	private String name;
	private double salary;
	private int numberOfAttempts;
	//@ElementCollection(fetch = FetchType.EAGER) //not recommended though possible
	@ElementCollection(fetch=FetchType.LAZY)  //recommeded approach when data needed using jpql
	@CollectionTable(name="Students_Subjects",joinColumns=@JoinColumn(name="rollno_fk"))
	private List<String> subjectsLearning;
	@Transient
	private LocalDate dateOfBirth;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return (int) (o.salary - this.salary);
	}
	
	
	
	
	
}