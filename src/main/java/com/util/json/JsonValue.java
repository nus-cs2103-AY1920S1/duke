package com.util.json;

import java.util.ArrayList;
import java.util.HashMap;

class DynamicValue {

    private ValueTypes type;
    private int valueInt;
    private double valueDouble;
    private boolean valueBoolean;
    private String valueString;
    private HashMap<String,DynamicValue> valueObject;
    private ArrayList<DynamicValue> valueArray;

    public DynamicValue(int value) {
        type = ValueTypes.INT;
        valueInt = value;
    }

    public DynamicValue(double value) {
        type = ValueTypes.DOUBLE;
        valueDouble = value;
    }

    public DynamicValue(boolean value) {
        type = ValueTypes.BOOLEAN;
        valueBoolean = value;
    }

    public DynamicValue(String value) {
        type = ValueTypes.STRING;
        valueString = value;
    }

    public DynamicValue(HashMap<String,DynamicValue> value) {
        type = ValueTypes.OBJECT;
        valueObject = value;
    }

    public DynamicValue(ArrayList<DynamicValue> value) {
        type = ValueTypes.ARRAY;
        valueArray = value;
    }

    public ValueTypes getType() {
        return type;
    }

    public int getInt() {
        return valueInt;
    }

    public double getDouble() {
        return valueDouble;
    }

    public boolean getBoolean() {
        return valueBoolean;
    }

    public String getString() {
        return valueString;
    }

    public HashMap<String,DynamicValue> getObject() {
        return valueObject;
    }

    public ArrayList<DynamicValue> getArray() {
        return valueArray;
    }
}
