package com.azamora.almundotest;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import org.awaitility.Duration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.entities.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlmundotestApplicationTests {

	@Autowired
	Dispatcher dispatcher;

	PriorityBlockingQueue<Employee> employees;
	
	@Before
	public void setUp() {
		employees = new PriorityBlockingQueue<>();
		employees.add(new Employee(Role.DIRECTOR,"Laura"));
		employees.add(new Employee(Role.SUPERVISOR,"Paola"));
		employees.add(new Employee(Role.SUPERVISOR,"Alfonso"));
		employees.add(new Employee(Role.OPERATOR,"Anna"));
		employees.add(new Employee(Role.OPERATOR,"Pietro"));
		employees.add(new Employee(Role.OPERATOR,"Daniele"));
	}

	@Test
	public void givenDispatcher_whenTheCallsAreTen_thenAvailableSlots6() throws InterruptedException {
	
		List<Call> calls = getCall(10);

		dispatcher.setEmployees(employees).setCalls(calls).buildSemaphore().dispatchCalls();

		await().atMost(Duration.ONE_MINUTE).until(() -> dispatcher.availableSlots() == 6);

		assertEquals(6, dispatcher.availableSlots());
	}
	
	@Test
	public void givenDispatcher_whenTheCallsAreLessThanTen_thenAvailableSlots3() throws InterruptedException {
	
		List<Call> calls = getCall(3);

		dispatcher.setEmployees(employees).setCalls(calls).buildSemaphore().dispatchCalls();

		await().atMost(Duration.ONE_MINUTE).until(() -> dispatcher.availableSlots() == 3);

		assertEquals(3, dispatcher.availableSlots());
	}
	
	
	@Test
	public void givenDispatcher_whenTheCallsAreMoreThanTen_thenAvailableSlots6() throws InterruptedException {
	
		List<Call> calls = getCall(20);

		dispatcher.setEmployees(employees).setCalls(calls).buildSemaphore().dispatchCalls();

		await().atMost(Duration.ONE_MINUTE).until(() -> dispatcher.availableSlots() == 6);

		assertEquals(6, dispatcher.availableSlots());
	}
	
	
	
	private List<Call> getCall(int numberOfCalls) {
		List<Call> calls = new ArrayList<>(numberOfCalls);
		for (int i = 0; i < numberOfCalls; i++) {
			calls.add(new Call());
		}
		return calls;
		

	}

}
