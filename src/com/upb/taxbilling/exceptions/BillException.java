package com.upb.taxbilling.exceptions;

/**
 * Represents the exception that occurs typically because a bill's error. 
 * @author Allan Leon
 */
public class BillException extends Exception {
	
	/**
	 * Constructor that receives a message as a parameter.
	 * @param message of the exception.
	 */
	public BillException(String message) {
		super(message);
	}
}
