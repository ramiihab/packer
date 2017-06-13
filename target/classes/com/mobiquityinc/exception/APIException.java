package com.mobiquityinc.exception;


/*
 * A custom Created Exception to be thrown by the Packer class.
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public APIException() {}

    public APIException(String message)
    {
       super(message);
    }
}
