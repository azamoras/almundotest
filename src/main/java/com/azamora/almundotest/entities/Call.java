package com.azamora.almundotest.entities;


import java.util.Random;

public class Call {
	static Random r = new Random();
	
	public long getDuration() {
		long max = 10000;
		long min = 5000;
		return r.longs(min, (max + 1)).limit(1).findFirst().getAsLong();
	}

}
