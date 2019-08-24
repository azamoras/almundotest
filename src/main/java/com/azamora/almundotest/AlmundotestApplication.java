package com.azamora.almundotest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.entities.Role;

@SpringBootApplication
public class AlmundotestApplication implements CommandLineRunner{

	@Autowired
	Dispatcher dispatcher;
	
	public static void main(String[] args) {
		SpringApplication.run(AlmundotestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<>();

		employees.add(new Employee(Role.DIRECTOR));
		employees.add(new Employee(Role.SUPERVISOR));
		employees.add(new Employee(Role.SUPERVISOR));
		employees.add(new Employee(Role.OPERATOR));
		employees.add(new Employee(Role.OPERATOR));
		employees.add(new Employee(Role.OPERATOR));

		List<Call> calls = new LinkedList<>();
		calls.add(new Call()); 
		calls.add(new Call());
	
		
		
		dispatcher.setEmployees(employees).setCalls(calls).buildSemaphore().dispatchCalls();
	

	}

}
