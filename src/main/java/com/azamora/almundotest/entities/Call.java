package com.azamora.almundotest.entities;


import java.util.Random;
import java.util.UUID;



public class Call  {
	private static Random r = new Random();
	private static long max = 10000;
	private static long min = 5000;
	
	private String identifier;


	
    public Call() {
    	identifier = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	

	public long getDuration() {
		return r.longs(min, (max + 1)).limit(1).findFirst().getAsLong();
	}
	
	
	@Override
	public String toString() {
		return "Call{" + "identifier='" + getIdentifier() + "\'}";
	}
}
