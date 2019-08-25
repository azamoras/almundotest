package com.azamora.almundotest.services;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;

@Service
public class CallServiceImpl implements CallService  {
	
	@Autowired
	private EmployeeQueueService employeeQueueService;
	

	private Logger logger = LoggerFactory.getLogger(CallServiceImpl.class);
	
	
	@Async 
	@Override
	public void startCall(Semaphore sem,Employee employee,Call call) {
		

		try {
			 
			logger.info(String.format("La llamada con identificador '%1$s' esta esperando a ser atendida...", call.getIdentifier()));
			
			sem.acquire();
			
            logger.info(String.format("La llamada con identificador '%1$s' esta siendo atendida por el empleado '%2$s'.",   call.getIdentifier(), employee));      
            
            Thread.sleep( call.getDuration());
             
            logger.info(String.format("La llamada con identificador '%1$s' ha terminado.",  call.getIdentifier()));
            
            employeeQueueService.offer(employee);
            
            sem.release();
            
            logger.info(String.format("Empleados libres %1$s", sem.availablePermits()));
        } catch (InterruptedException   e) {
        	logger.error("Error", e);
        	Thread.currentThread().interrupt();
        } 

	}


	


}
