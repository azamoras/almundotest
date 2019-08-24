package com.azamora.almundotest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;
import com.azamora.almundotest.entities.Type;
import com.azamora.almundotest.utils.StandBy;

@SpringBootApplication
public class AlmundotestApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AlmundotestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<>();

		employees.add(new Employee(Type.DIRECTOR));
		employees.add(new Employee(Type.SUPERVISOR));
		employees.add(new Employee(Type.SUPERVISOR));
		employees.add(new Employee(Type.OPERATOR));
		employees.add(new Employee(Type.OPERATOR));
		employees.add(new Employee(Type.OPERATOR));

		Semaphore semaphore =new Semaphore(employees.size());
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
		Dispatcher dispatcher10 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher10.start();
		Dispatcher dispatcher12 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher12.start();
		Dispatcher dispatcher13 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher13.start();
		Dispatcher dispatcher14 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher14.start();
		Dispatcher dispatcher15 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher15.start();
		Dispatcher dispatcher16 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher16.start();
		Dispatcher dispatcher17 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher17.start();
		Dispatcher dispatcher18 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher18.start();
		Dispatcher dispatcher19 = new Dispatcher(employees,semaphore,standByMessage, new Call());
		dispatcher19.start();


	}

}
