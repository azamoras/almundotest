package com.azamora.almundotest.entities;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Employee implements Comparable<Employee> {

	private Logger logger = LoggerFactory.getLogger(Employee.class);
	
	private Type type;
	private String name;
	private Call call;
	private Semaphore sem; 
	private CyclicBarrier barrier;

	public Employee(Type type) {
		super();
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	public String getEmployeeName() {
		return name;
	}

	public void setEmployeeName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(name, employee.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", type=" + type +'}';
	}

	@Override
	public int compareTo(Employee employee) {
		if(this.getType().getValue() > employee.getType().getValue()) {
            return 1;
        } else if (this.getType().getValue() < employee.getType().getValue()) {
            return -1;
        } else {
            return 0;
        }
	
	}

	
	
	


}
