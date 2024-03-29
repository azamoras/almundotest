package com.azamora.almundotest.entities;

import java.util.Objects;

/**
 * Clase Empleado.
 *
 * @author adrian
 *
 */

public class Employee  implements Comparable<Employee>{

	private Role role;
	private String name;


	public Employee(Role role,String name) {
		super();
		this.role = role;
		this.name = name;
	}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmployeeName() {
		return name;
	}

	public void setEmployeeName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(name, employee.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", role=" + role +'}';
	}

	/**
	 * <p>Permite comparar un Empleado con otro
	 * </p>
	 *
	 * @param employee a comparar.
	 * @return devuelve 1 si es mayor, -1 si es menor o 0 si son iguales.
	 */
	@Override
	public int compareTo(Employee employee) {
		if(this.getRole().getValue() > employee.getRole().getValue()) {
            return 1;
        } else if (this.getRole().getValue() < employee.getRole().getValue()) {
            return -1;
        } else {
            return 0;
        }
	
	}
	





	
	
	


}
