package com.sjsu.bikelet.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ckempaiah
 * Date: 2/11/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class BikeletValidationException extends RuntimeException {

    public BikeletValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BikeletValidationException(String message) {
        super(message);
    }
}
