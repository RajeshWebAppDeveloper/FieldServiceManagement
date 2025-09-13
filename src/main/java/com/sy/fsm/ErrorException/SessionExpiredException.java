package com.sy.fsm.ErrorException;

public class SessionExpiredException extends RuntimeException {
    
	private static final long serialVersionUID = -8773242828675149148L;

	public SessionExpiredException(String message) {
        super(message);
    }

    public SessionExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}