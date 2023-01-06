package com.tanphi.laptopshop.exception;

public class SendMailException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SendMailException(String message) {
        super(message);
    }
}
