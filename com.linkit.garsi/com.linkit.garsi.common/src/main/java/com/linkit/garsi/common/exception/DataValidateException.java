package com.linkit.garsi.common.exception;

public class DataValidateException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1741078348564045051L;
    private final String message;
    private int errorCode = 1000;

    public DataValidateException() {
        this.message = "";
    }

    public DataValidateException(String message) {
        this.message = message;
    }


    public DataValidateException(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }


    public DataValidateException(String message, Throwable t) {
        super(message, t);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
