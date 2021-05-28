package com.project.exception;

/**
 * 
 * @author shivam
 *
 */
public class AppRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -2079883834206817053L;
	private Exception exception;

	public AppRuntimeException(String message) {
		super(message);
	}

	public AppRuntimeException(Exception e) {
		super(e.getMessage());
		this.exception = e;
	}

	public AppRuntimeException(String message, Exception e) {
		super(message);
		this.exception = e;
	}

	public Exception getException() {
		return exception;
	}

}
