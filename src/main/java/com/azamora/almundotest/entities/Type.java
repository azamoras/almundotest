package com.azamora.almundotest.entities;



public enum Type {
	
	OPERATOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);
   
    private final int value;
    
    private Type(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    
}
