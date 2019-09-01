package com.util.json;

public class SaveFileFormatException extends Exception {

    /**
     * 1 - failed due to content of file
     * 2 - failed due to wrong prediction of value data type
     */
    private int errorCode;

    public SaveFileFormatException() {
        super();
        errorCode = 1;
    }

    public SaveFileFormatException(String msg, Integer err) {
        super(msg);
        errorCode = err;
    }

    public SaveFileFormatException(String msg) {
        super(msg);
        errorCode = 1;
    }

    public int errorCode() {
        return errorCode;
    }
}
