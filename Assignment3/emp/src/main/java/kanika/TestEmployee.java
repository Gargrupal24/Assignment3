
package kanika;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import in.co.vwits.model.exception.EmployeeNotFoundException;
import in.co.vwits.service.EmployeeService;
import in.co.vwits.service.impl.EmployeeServiceImpl;
import in.co.vwits.sms.model.Employee;

public class TestEmployee {

	public static void main(String[] args) throws EmployeeNotFoundException {
		int option=1;
		Scanner sc = null;
		try {
			sc= new Scanner(System.in);
		
		
	
		EmployeeService service = new EmployeeServiceImpl();
		
				
		//Show all students
		
		do {
			System.out.println("Welcome to employee mangement ");
			System.out.println("1 Find all records");
			System.out.println("2 Insert Re"
					+ ""
					+ "cords");
			System.out.println("3 Find employee by ID.");
			System.out.println("4 Delete employee by Id.");
			System.out.println("5 Update employee by Id.");
			System.out.println("-1 to Exit");
			System.out.println("Enter choice");
			
			option= sc.nextInt();
			
			switch(option) {
			case 1:
				List<Employee> employees = service.findAll();
				for(Employee employee: employees) {
					System.out.println(employee.getId()+" "+employee.getName()+" ");
				}
				System.out.println("After");
			    System.out.println(employees);
			    break;

			case 2:
				Employee e=new Employee();
				//Accept value from user
				System.out.println("Enter Name of Employee:");
				e.setName(sc.next());
				System.out.println("Enter id:");
				e.setId(sc.nextInt());  
				System.out.println("Enter Salary of Employee: ");
				e.setSalary(sc.nextDouble());
				
				service.save(e);
				System.out.println("Data Saved");
				
				break;
			case 3:
				System.out.println("Enter the id.");
				int id=sc.nextInt();
				Optional<Employee> foundemployee;
				
				try {
					
					foundemployee = service.findById(id);
					System.out.println("Found Student"+foundemployee.get());
				} catch (EmployeeNotFoundException e1) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Student Not Found");
				}
				
				break;
			case 4:
				System.out.println("Enter the id.");
				try {
					id=sc.nextInt();
					service.deleteById(id);
				}
				catch(InputMismatchException e1) {
					System.out.println("Id must be an Integer value");
					sc.nextLine(); //This statement is used to discard the input.
					
					
				}
				
				
				break;
			
			case 5:
				double modifiedSalary;
				System.out.println("Enter ID.");
				id=sc.nextInt();
				foundemployee = Optional.ofNullable(service.findById(id).get());
				Employee ee= foundemployee.get();
				System.out.println("Enter new salary");
				modifiedSalary=sc.nextDouble();
				ee.setSalary(modifiedSalary);
				service.updateById(ee);
				break;
				
				
			case -1:
				System.out.println("Thank You");
				break;
				
			}
			
		}while(option!=-1);

	}
	finally {
		sc.close();
	}
	}
}
