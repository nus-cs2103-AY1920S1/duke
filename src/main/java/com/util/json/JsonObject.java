package com.util.json;

import com.util.Printer;

public class JsonObject {

    /**
     * True if object has no keys, False if a key exists.
     */
    private boolean empty = true;
    /**
     * String used to hold current json format.
     */
    private String formattedString = "";

    /**
     * Appends a Json formatted string value into this object.
     * @param key   string key
     * @param value Json formatted value
     * @return      this object
     */
    private JsonObject putLiteral(String key, String value) {
        if (!empty) {
            formattedString += ",\n";
        }
        formattedString += Printer.surroundQuotes(key) + ": " + value;
        empty = false;
        return this;
    }

    /**
     * Call to insert a string into the object.
     * @param key   string key
     * @param value string value
     * @return      this object
     */
    public JsonObject put(String key, String value) {
        return putLiteral(key, Printer.surroundQuotes(value));
    }

    /**
     * Call to insert non string values into the object.
     * @param key   string key
     * @param value object value
     * @return      this object
     */
    public JsonObject put(String key, Object value) {
        return putLiteral(key, value.toString());
    }

    /**
     * Returns string representation of JsonObject.
     * @return  string representation
     */
    public String toString() {
        return "{\n" + Printer.indentString(formattedString) + "}";
    }
}
