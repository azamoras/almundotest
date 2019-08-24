package com.azamora.almundotest.entities;


import java.util.Random;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azamora.almundotest.services.EmployeeQueueService;



public class Call extends Thread  {
	private static Random r = new Random();
	private static long max = 10000;
	private static long min = 5000;
	
	private Logger logger = LoggerFactory.getLogger(Employee.class);
	private Semaphore sem; 

	private Employee employee;
	
	private EmployeeQueueService employeeQueueService;

	
	public void startCall(Semaphore sem,Employee employee,EmployeeQueueService employeeQueueService) {
		this.employee = employee;
		this.employeeQueueService = employeeQueueService;
		this.sem = sem;
		this.start();

	}

	public long getDuration() {
		return r.longs(min, (max + 1)).limit(1).findFirst().getAsLong();
	}
	
	
	@Override
	public void run() {

		try {
			 
			logger.info(" is waiting to speak to the operator...");
			this.sem.acquire();
            logger.info(this.employee.getRole()+ " is getting the connection to the operator...");      
            
            Thread.sleep(getDuration());
             
            logger.info(this.employee.getRole()+ "´s phone call with the operator ending.");
            
            employeeQueueService.offer(employee);
            
            this.sem.release();
            
            logger.info("Available operators="+ sem.availablePermits());
             
        } catch (InterruptedException   e) {
        	logger.error("Error", e);
        } 
	}
}
