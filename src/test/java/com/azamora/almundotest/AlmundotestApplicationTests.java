package com.azamora.almundotest;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.entities.Type;
import com.azamora.almundotest.utils.StandBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlmundotestApplicationTests {

	@Test
	public void contextLoads() {
		PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<>();
		
		employees.add(new Employee(Type.DIRECTOR));
		employees.add(new Employee(Type.SUPERVISOR));
		employees.add(new Employee(Type.SUPERVISOR));
		employees.add(new Employee(Type.OPERATOR));
		employees.add(new Employee(Type.OPERATOR));
		employees.add(new Employee(Type.OPERATOR));
		
		 Semaphore semaphore =new Semaphore(employees.size());;
		 CyclicBarrier standByMessage = new CyclicBarrier(employees.size(), new StandBy());
		List<Call> calls = new LinkedList<>();
		calls.add(new Call());
		calls.add(new Call());
		calls.add(new Call());
		calls.add(new Call());
		calls.add(new Call());
		calls.add(new Call());
		
		Dispatcher dispatcher = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher.start();
		Dispatcher dispatcher2 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher2.start();
		Dispatcher dispatcher3 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher3.start();
		Dispatcher dispatcher4 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher4.start();
		Dispatcher dispatcher5 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher5.start();
		Dispatcher dispatcher6 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher6.start();
		Dispatcher dispatcher7 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher7.start();
		Dispatcher dispatcher8 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher8.start();
		Dispatcher dispatcher9 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher9.start();
		
		//assertEquals(0, dispatcher.availableSlots());
	}

}
