package com.azamora.almundotest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.exception.NoEmployeeAvailableException;


public class Dispatcher extends Thread {

	private Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	
	private PriorityBlockingQueue<Employee> employees;
	private Call calls;
	private Semaphore semaphore;
	private CyclicBarrier standByMessage;




	public Dispatcher(PriorityBlockingQueue<Employee> employees,Semaphore semaphore,CyclicBarrier standByMessage,Call calls) {
		super();
		this.employees = employees;
		this.calls = calls;
		this.semaphore = semaphore;
		this.standByMessage = standByMessage;
	}


	@Override
	public void run() {
		dispatchCall(calls);
		
	}


	public void dispatchCall(Call call) {

			try {
				 
				logger.info(" is waiting to speak to the operator...");
	 
	            this.semaphore.acquire();
	            
	            Employee selected = getAvailableEmployee();
	 
	            logger.info(selected.getType()+ " is getting the connection to the operator...");
	                       
	            Thread.sleep(call.getDuration());
	             
	            logger.info(selected.getType()+ "´s phone call with the operator ending.");
	            
	            this.employees.offer(selected);
	            
	            this.semaphore.release();
	             
	            logger.info("Available operators="+ semaphore.availablePermits());
	            
	        	
	             
	        } catch (InterruptedException   e) {
	        	logger.error("Error", e);
	        } 

	}

	

	private  Employee getAvailableEmployee() throws InterruptedException {
		return employees.take();
	
	}



	public int availableSlots() {
		return this.semaphore.availablePermits();

	}


}
