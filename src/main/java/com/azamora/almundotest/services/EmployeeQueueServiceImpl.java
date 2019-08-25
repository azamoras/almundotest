package com.azamora.almundotest.services;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Service;

import com.azamora.almundotest.entities.Employee;

@Service
public class EmployeeQueueServiceImpl implements EmployeeQueueService {

	private  PriorityBlockingQueue<Employee> employees;
	
	
	@Override
	public void setEmployees(PriorityBlockingQueue<Employee> employees) {
		this.employees = employees;
		
	}

	@Override
	public void offer(Employee employee) {
		this.employees.offer(employee);
		
	}

	@Override
	public Employee getAvailableEmployee() throws InterruptedException {
		 return this.employees.take();
		
	}

	@Override
	public int size() {
		return this.employees.size();
	}

	@Override
	public PriorityBlockingQueue<Employee> getEmployees() {
		
		return this.employees;
	}

}
