package com.util.json;

import com.util.Printer;

public class JsonArray {

    private boolean empty = true;
    private String formattedString = "";

    private JsonArray putLiteral(String value) {
        if (!empty) {
            formattedString += ",\n";
        }
        formattedString += value;
        empty = false;
        return this;
    }

    public JsonArray put(String value) {
        return putLiteral(Printer.surroundQuotes(value));
    }

    public JsonArray put(Object value) {
        return putLiteral(value.toString());
    }

    public String toString() {
        return "[\n" + Printer.indentString(formattedString) + "]";
    }
}
