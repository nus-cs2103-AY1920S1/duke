package com.util.json;

public class JsonValue {

    private ValueTypes type;
    private int valueInt;
    private double valueDouble;
    private boolean valueBoolean;
    private String valueString;
    private JsonObject valueObject;
    private JsonArray valueArray;

    /**
     * Construct the value as an int.
     * @param value int value
     */
    public JsonValue(int value) {
        type = ValueTypes.INT;
        valueInt = value;
    }

    /**
     * Construct the value as a double.
     * @param value double value
     */
    public JsonValue(double value) {
        type = ValueTypes.DOUBLE;
        valueDouble = value;
    }

    /**
     * Construct the value as a boolean.
     * @param value boolean value
     */
    public JsonValue(boolean value) {
        type = ValueTypes.BOOLEAN;
        valueBoolean = value;
    }

    /**
     * Construct the value as a string.
     * @param value string value
     */
    public JsonValue(String value) {
        type = ValueTypes.STRING;
        valueString = value;
    }

    /**
     * Construct the value as a JsonObject.
     * @param value JsonObject value
     */
    public JsonValue(JsonObject value) {
        type = ValueTypes.OBJECT;
        valueObject = value;
    }

    /**
     * Construct the value as a JsonArray.
     * @param value JsonArray value
     */
    public JsonValue(JsonArray value) {
        type = ValueTypes.ARRAY;
        valueArray = value;
    }

    /**
     * Get the type of this value.
     * @return  type
     */
    public ValueTypes getType() {
        return type;
    }

    /**
     * Get the value if it is an int.
     * @return  value
     * @throws JsonWrongValueTypeException  exception if value type is not a match
     */
    public int getInt() throws JsonWrongValueTypeException {
        if (type != ValueTypes.INT) {
            throw new JsonWrongValueTypeException(ValueTypes.INT, type);
        }
        return valueInt;
    }

    /**
     * Get the value if it is an double.
     * @return  value
     * @throws JsonWrongValueTypeException  exception if value type is not a match
     */
    public double getDouble() throws JsonWrongValueTypeException {
        if (type != ValueTypes.DOUBLE) {
            throw new JsonWrongValueTypeException(ValueTypes.DOUBLE, type);
        }
        return valueDouble;
    }

    /**
     * Get the value if it is an boolean.
     * @return  value
     * @throws JsonWrongValueTypeException  exception if value type is not a match
     */
    public boolean getBoolean() throws JsonWrongValueTypeException {
        if (type != ValueTypes.BOOLEAN) {
            throw new JsonWrongValueTypeException(ValueTypes.BOOLEAN, type);
        }
        return valueBoolean;
    }

    /**
     * Get the value if it is an string.
     * @return  value
     * @throws JsonWrongValueTypeException  exception if value type is not a match
     */
    public String getString() throws JsonWrongValueTypeException {
        if (type != ValueTypes.STRING) {
            throw new JsonWrongValueTypeException(ValueTypes.STRING, type);
        }
        return valueString;
    }

    /**
     * Get the value if it is an JsonObject.
     * @return  value
     * @throws JsonWrongValueTypeException  exception if value type is not a match
     */
    public JsonObject getObject() throws JsonWrongValueTypeException {
        if (type != ValueTypes.OBJECT) {
            throw new JsonWrongValueTypeException(ValueTypes.OBJECT, type);
        }
        return valueObject;
    }

    /**
     * Get the value if it is an JsonArray.
     * @return  value
     * @throws JsonWrongValueTypeException  exception if value type is not a match
     */
    public JsonArray getArray() throws JsonWrongValueTypeException {
        if (type != ValueTypes.ARRAY) {
            throw new JsonWrongValueTypeException(ValueTypes.ARRAY, type);
        }
        return valueArray;
    }

    /**
     * Get string representation of object.
     * @return  string representation
     */
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
