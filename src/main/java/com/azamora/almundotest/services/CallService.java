package com.azamora.almundotest.services;

import java.util.concurrent.Semaphore;

import com.azamora.almundotest.entities.Call;
import com.azamora.almundotest.entities.Employee;

/**
 * Interfaz para servicios de llamadas
 *
 * @author adrian
 *
 */
public interface CallService {

	 public abstract void startCall(Semaphore sem,Employee employee,Call call);
}
