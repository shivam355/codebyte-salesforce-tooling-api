package com.project.exception;

/**
 * Custom exception. Can be improved.
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

	@Override
	public String toString() {
		return "Issue - " + this.getMessage();
	}

}
