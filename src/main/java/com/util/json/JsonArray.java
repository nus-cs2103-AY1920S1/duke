package com.util.json;

import com.util.Printer;

public class JsonArray {

    /**
     * True if array has no values, False if a value exists.
     */
    private boolean empty = true;
    /**
     * String used to hold current json format.
     */
    private String formattedString = "";

    /**
     * Appends a Json formatted string value into this array.
     * @param value Json formatted string
     * @return      this array
     */
    private JsonArray putLiteral(String value) {
        if (!empty) {
            formattedString += ",\n";
        }
        formattedString += value;
        empty = false;
        return this;
    }

    /**
     * Call to insert a string into the array.
     * @param value string to insert
     * @return      this array
     */
    public JsonArray put(String value) {
        return putLiteral(Printer.surroundQuotes(value));
    }

    /**
     * Call to insert non string values into the array.
     * @param value object to insert
     * @return      this array
     */
    public JsonArray put(Object value) {
        return putLiteral(value.toString());
    }

    /**
     * Returns string representation of JsonArray.
     * @return  string representation
     */
    public String toString() {
        return "[\n" + Printer.indentString(formattedString) + "]";
    }
}
