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


/**
 * Clase encargada de encargada de manejar las llamadas.
 *
 * @author adrian
 *
 */
@Component
public class Dispatcher  {

	private Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	/**
	 * Semáforo para manejar la cantidad de llamadas a atender según la cantidad de empleados.
	 */
	private Semaphore semaphore;

	/**
	 *  Listados de llamadas a atender, no importa el tamaño.
	 */
	private List<Call> callList;

	/**
	 *  Servicio que maneja la cola de empleados.
	 */
	@Autowired
	EmployeeQueueService employeeQueueService;

	/**
	 *  Servicio que realiza la llamada.
	 */
	@Autowired
	CallService callServices;

	/**
	 * <p>Valida que no sean null o vacía los datos entrantes.
	 * </p>
	 *
	 */
	private void validate() {
		Assert.notEmpty(this.employeeQueueService.getEmployees(), "No existen empleados");
		Assert.notEmpty(this.callList, "No hay llamadas a procesar");
	}

	/**
	 * <p>Permite especificar los empleados.
	 * </p>
	 * @param employees colección de empleados.
	 * @return devuelve la misma instancia de Dispatcher.
	 */
	public Dispatcher setEmployees(PriorityBlockingQueue<Employee> employees) {
		this.employeeQueueService.setEmployees(employees);
		return this;
	}

	/**
	 * <p>Permite especificar las llamadas.
	 * </p>
	 * @param callList listado de llamadas.
	 * @return devuelve la misma instancia de Dispatcher.
	 */
	public Dispatcher setCalls(List<Call> callList) {
		this.callList = callList;
		return this;
	}

	/**
	 * <p>Construye el semáforo a partir de la cantidad de empleados.
	 * </p>
	 * @return devuelve la misma instancia de Dispatcher.
	 */

	public Dispatcher buildSemaphore() {
		validate();
		this.semaphore = new Semaphore(this.employeeQueueService.size());
		return this;
	}


	/**
	 * <p>Ejecuta las llamadas a partir del listado .
	 * </p>
	 *
	 */

	public void dispatchCalls() {
		logger.info("Empleados listos para tomar llamadas {}", this.semaphore.availablePermits());
		
		callList.forEach(this::dispatchCall);
	}

	/**
	 * <p>Ejecuta una llamada. Le asigna un empleado.
	 * </p>
	 * @param call llamada a realizar
	 *
	 */
	public  void dispatchCall(Call call) {

		try {
			Employee selected = employeeQueueService.getAvailableEmployee();
			
			callServices.startCall(this.semaphore, selected, call);

		} catch (InterruptedException  e) {
			logger.error("Error", e);
			Thread.currentThread().interrupt();
		}

	}


	/**
	 * <p>Permite obtener la cantidad de empleados libres
	 * </p>
	 * @return  cantidad de empleados disponibles
	 *
	 */
	public int availableSlots() {
		return this.semaphore.availablePermits();

	}


}
