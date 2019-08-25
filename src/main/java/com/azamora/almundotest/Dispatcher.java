package com.azamora.almundotest;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.services.CallService;
import com.azamora.almundotest.services.EmployeeQueueService;


@Component
public class Dispatcher  {

	private Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	private Semaphore semaphore;
	private List<Call> callList;


	@Autowired
	EmployeeQueueService employeeQueueService;
	
	@Autowired
	CallService callServices;


	private void validate() {
		Assert.notEmpty(this.employeeQueueService.getEmployees(), "No existen empleados");
		Assert.notEmpty(this.callList, "No hay llamadas a procesar");
	}

	public Dispatcher setEmployees(PriorityBlockingQueue<Employee> employees) {
		this.employeeQueueService.setEmployees(employees);
		return this;
	}
	public Dispatcher setCalls(List<Call> callList) {
		this.callList = callList;
		return this;
	}

	public Dispatcher buildSemaphore() {
		validate();
		this.semaphore = new Semaphore(this.employeeQueueService.size());
		return this;
	}



	public void dispatchCalls() {
		logger.info(String.format("Empleados listos para tomar llamadas %1$s", this.semaphore.availablePermits()));
		
		callList.forEach(call-> {this.dispatchCall(call);});
	}


	public void dispatchCall(Call call) {

		try {
			Employee selected = employeeQueueService.getAvailableEmployee();
			
			callServices.startCall(this.semaphore, selected, call);

		} catch (InterruptedException  e) {
			logger.error("Error", e);
			Thread.currentThread().interrupt();
		}

	}



	public int availableSlots() {
		return this.semaphore.availablePermits();

	}


}
