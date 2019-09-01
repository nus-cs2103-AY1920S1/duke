package com.util.json;

public class SaveFileFormatException extends Exception {

    /**
     * 1 - failed due to content of file.
     * 2 - failed due to wrong prediction of value data type.
     */
    private int errorCode;

    /**
     * Construct the exception without message and default errorCode.
     */
    public SaveFileFormatException() {
        super();
        errorCode = 1;
    }

    /**
     * Construct the exception with message and errorCode.
     * @param msg   message
     * @param err   errorCode
     */
    public SaveFileFormatException(String msg, Integer err) {
        super(msg);
        errorCode = err;
    }

    /**
     * Construct the exception with message and default errorCode.
     * @param msg   message
     */
    public SaveFileFormatException(String msg) {
        super(msg);
        errorCode = 1;
    }

    /**
     * Get error code of object.
     * @return  errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }
}
