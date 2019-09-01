package com.util.json;

import com.util.Printer;

public class JsonObject {

    private boolean empty = true;
    private String formattedString = "";

    private JsonObject putLiteral(String key, String value) {
        if (!empty) {
            formattedString += ",\n";
        }
        formattedString += Printer.surroundQuotes(key) + ": " + value;
        empty = false;
        return this;
    }

    public JsonObject put(String key, String value) {
        return putLiteral(key, Printer.surroundQuotes(value));
    }

    public JsonObject put(String key, Object value) {
        return putLiteral(key, value.toString());
    }

    public String toString() {
        return "{\n" + Printer.indentString(formattedString) + "}";
    }
}
