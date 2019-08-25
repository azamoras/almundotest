package com.azamora.almundotest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandBy implements Runnable {
	 
	private Logger logger = LoggerFactory.getLogger(StandBy.class);
	
    @Override
    public void run() {
    	logger.info("All operators are busy at the moment - playing MUSIC ...;");
    }
 
}