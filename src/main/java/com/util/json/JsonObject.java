package com.util.json;

import com.util.Printer;

public class JsonObject {

    private boolean empty = true;
    private String formattedString = "";

    private JsonObject putLiteral(String key, String value) {
        if (!empty) {
            formattedString += ",\n";
        }
        formattedString += surroundQuotes(key) + ": " + value;
        empty = false;
        return this;
    }

    public JsonObject put(String key, String value) {
        return putLiteral(key, surroundQuotes(value));
    }

    public JsonObject put(String key, Integer value) {
        return putLiteral(key, value.toString());
    }

    public JsonObject put(String key, Double value) {
        return putLiteral(key, value.toString());
    }

    public JsonObject put(String key, JsonObject value) {
        return putLiteral(key, value.toString());
    }

    public String toString() {
        return "{\n" + Printer.indentString(formattedString) + "}";
    }

    private String surroundQuotes(String str) {
        return "\"" + str + "\"";
    }
}
