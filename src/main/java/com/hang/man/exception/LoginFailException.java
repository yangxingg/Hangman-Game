package com.hang.man.exception;

public class LoginFailException extends Exception{
	private static final long serialVersionUID = 1L;

    public LoginFailException(String message) {
        super(message);
    }
}
