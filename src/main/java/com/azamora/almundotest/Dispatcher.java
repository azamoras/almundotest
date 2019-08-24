package com.azamora.almundotest;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.services.EmployeeQueueService;


@Component
public class Dispatcher  {

	private Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	private Semaphore semaphore;
	private List<Call> callList;
	
	
	@Autowired
	EmployeeQueueService employeeQueueService;

	public Dispatcher setEmployees(PriorityBlockingQueue<Employee> employees) {
		this.employeeQueueService.setEmployees(employees);
		return this;
	}
	public Dispatcher setCalls(List<Call> callList) {
		this.callList = callList;
		return this;
	}
	
	public Dispatcher buildSemaphore() {
		this.semaphore = new Semaphore(this.employeeQueueService.size());
		return this;
	}



	public void dispatchCalls() {
		callList.forEach(call-> {
			this.dispatchCall(call);
		});
	}


	public void dispatchCall(Call call) {

		try {
			Employee selected = employeeQueueService.getAvailableEmployee();
			call.startCall(this.semaphore,selected,employeeQueueService);
		
		} catch (InterruptedException  e) {
			logger.error("Error", e);

		}

	}



	public int availableSlots() {
		return this.semaphore.availablePermits();

	}


}
