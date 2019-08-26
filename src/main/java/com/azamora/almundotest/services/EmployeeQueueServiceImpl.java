package com.azamora.almundotest.services;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Service;

import com.azamora.almundotest.entities.Employee;


/**
 * Clase servicio para manejar la cola de empleados.
 *
 * @author adrian
 *
 */
@Service
public class EmployeeQueueServiceImpl implements EmployeeQueueService {

	private  PriorityBlockingQueue<Employee> employees;


	/**
	 * <p>Permite agregar empleados a la lista.
	 * </p>
	 * @param employees colección de empleados.
	 */
	@Override
	public void setEmployees(PriorityBlockingQueue<Employee> employees) {
		this.employees = employees;
		
	}

	/**
	 * <p>Permite agregar un empleado a la cola.
	 * </p>
	 * @param employee empleado a agregar.
	 */
	@Override
	public void offer(Employee employee) {
		this.employees.offer(employee);
		
	}

	/**
	 * <p>Obtine empleado de la cola.
	 * </p>
	 * @return empleado.
	 */
	@Override
	public Employee getAvailableEmployee() throws InterruptedException {
		 return this.employees.take();
		
	}

	/**
	 * <p>Tamaño de la cola de empleados.
	 * </p>
	 * @return cantidad de empleados en la cola.
	 */
	@Override
	public int size() {
		return this.employees.size();
	}


	/**
	 * <p>Tamaño de la cola de empleados.
	 * </p>
	 *  @return Colección completa de empleados.
	 */
	@Override
	public PriorityBlockingQueue<Employee> getEmployees() {
		
		return this.employees;
	}

}
