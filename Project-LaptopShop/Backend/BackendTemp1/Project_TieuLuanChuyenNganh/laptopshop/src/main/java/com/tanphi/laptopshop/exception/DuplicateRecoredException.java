package com.tanphi.laptopshop.exception;

public class DuplicateRecoredException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateRecoredException(String message) {
        super(message);
    }
}
