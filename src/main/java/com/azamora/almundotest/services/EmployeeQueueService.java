package com.azamora.almundotest.services;

import java.util.concurrent.PriorityBlockingQueue;
import com.azamora.almundotest.entities.Employee;

/**
 * Interfaz para servicios de empleados
 *
 * @author adrian
 *
 */
public interface EmployeeQueueService {
	 public abstract void setEmployees(PriorityBlockingQueue<Employee> employees);
	 public abstract PriorityBlockingQueue<Employee> getEmployees();
	 public abstract int size();
	 public abstract void offer(Employee employee);
	 public abstract Employee getAvailableEmployee()  throws InterruptedException ;
}
