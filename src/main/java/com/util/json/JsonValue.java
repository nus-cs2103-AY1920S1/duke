package com.util.json;

public class JsonValue {

    private ValueTypes type;
    private int valueInt;
    private double valueDouble;
    private boolean valueBoolean;
    private String valueString;
    private JsonObject valueObject;
    private JsonArray valueArray;

    public JsonValue(int value) {
        type = ValueTypes.INT;
        valueInt = value;
    }

    public JsonValue(double value) {
        type = ValueTypes.DOUBLE;
        valueDouble = value;
    }

    public JsonValue(boolean value) {
        type = ValueTypes.BOOLEAN;
        valueBoolean = value;
    }

    public JsonValue(String value) {
        type = ValueTypes.STRING;
        valueString = value;
    }

    public JsonValue(JsonObject value) {
        type = ValueTypes.OBJECT;
        valueObject = value;
    }

    public JsonValue(JsonArray value) {
        type = ValueTypes.ARRAY;
        valueArray = value;
    }

    public ValueTypes getType() {
        return type;
    }

    public int getInt() throws JsonWrongValueTypeException {
        if (type != ValueTypes.INT) {
            throw new JsonWrongValueTypeException(ValueTypes.INT, type);
        }
        return valueInt;
    }

    public double getDouble() throws JsonWrongValueTypeException {
        if (type != ValueTypes.DOUBLE) {
            throw new JsonWrongValueTypeException(ValueTypes.DOUBLE, type);
        }
        return valueDouble;
    }

    public boolean getBoolean() throws JsonWrongValueTypeException {
        if (type != ValueTypes.BOOLEAN) {
            throw new JsonWrongValueTypeException(ValueTypes.BOOLEAN, type);
        }
        return valueBoolean;
    }

    public String getString() throws JsonWrongValueTypeException {
        if (type != ValueTypes.STRING) {
            throw new JsonWrongValueTypeException(ValueTypes.STRING, type);
        }
        return valueString;
    }

    public JsonObject getObject() throws JsonWrongValueTypeException {
        if (type != ValueTypes.OBJECT) {
            throw new JsonWrongValueTypeException(ValueTypes.OBJECT, type);
        }
        return valueObject;
    }

    public JsonArray getArray() throws JsonWrongValueTypeException {
        if (type != ValueTypes.ARRAY) {
            throw new JsonWrongValueTypeException(ValueTypes.ARRAY, type);
        }
        return valueArray;
    }

    public String toString() {
        try {
            switch (type) {
            case INT:
                return Integer.toString(getInt());
            case DOUBLE:
                return Double.toString(getDouble());
            case BOOLEAN:
                return Boolean.toString(getBoolean());
            case STRING:
                return JsonParser.formatStringForJson(getString());
            case OBJECT:
                return getObject().toString();
            case ARRAY:
                return getArray().toString();
            default:
                System.out.println("ERROR: unexpected json value type " + type);
                System.exit(-1);
            }
        } catch (JsonWrongValueTypeException e) {
            System.out.println("ERROR: json value type does not match value its holding");
            e.printStackTrace();
            System.exit(-1);
        }
        return "";
    }
}
