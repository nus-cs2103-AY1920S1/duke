package com.util.json;

public class JsonWrongValueTypeException extends Exception {
    JsonWrongValueTypeException(ValueTypes expected, ValueTypes got) {
        super("Expecting " + expected + ", however got " + got);
    }
}
