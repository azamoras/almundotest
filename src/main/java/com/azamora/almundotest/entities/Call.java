package com.azamora.almundotest.entities;


import java.util.Random;
import java.util.UUID;


/**
 * Clase Llamada.
 *
 * @author adrian
 *
 */
public class Call  {
	private static Random r = new Random();
	private static long max = 10000;
	private static long min = 5000;


	/**
	 *  Indentificador de la llamada.
	 */
	private String identifier;


	
    public Call() {
    	identifier = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

	/**
	 * <p>Permite obtener un identificador para la llamada.
	 * </p>
	 * @return devuelve un identificador aleatorio para la llamada.
	 */
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * <p>Permite obtener la duración de la llamada
	 * </p>
	 * @return devuelve la duracion de la llamada de manera aleatoria (5 o 10 segundos)
	 */
	public long getDuration() {
		return r.longs(min, (max + 1)).limit(1).findFirst().getAsLong();
	}
	
	
	@Override
	public String toString() {
		return "Call{" + "identifier='" + getIdentifier() + "\'}";
	}
}
