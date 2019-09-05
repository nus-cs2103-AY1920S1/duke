package com.util.json;

import com.util.Printer;
import java.util.ArrayList;

public class JsonArray extends ArrayList<JsonValue> {

    public boolean add(int value) {
        return super.add(new JsonValue(value));
    }

    public boolean add(double value) {
        return super.add(new JsonValue(value));
    }

    public boolean add(boolean value) {
        return super.add(new JsonValue(value));
    }

    public boolean add(String value) {
        return super.add(new JsonValue(value));
    }

    public boolean add(JsonObject value) {
        return super.add(new JsonValue(value));
    }

    public boolean add(JsonArray value) {
        return super.add(new JsonValue(value));
    }

    public void add(int index, int value) {
        super.add(index, new JsonValue(value));
    }

    public void add(int index, double value) {
        super.add(index, new JsonValue(value));
    }

    public void add(int index, boolean value) {
        super.add(index, new JsonValue(value));
    }

    public void add(int index, String value) {
        super.add(index, new JsonValue(value));
    }

    public void add(int index, JsonObject value) {
        super.add(index, new JsonValue(value));
    }

    public void add(int index, JsonArray value) {
        super.add(index, new JsonValue(value));
    }

    /**
     * Returns string representation of JsonArray.
     * @return  string representation
     */
    public String toString() {
        StringBuilder formattedString = new StringBuilder("");
        boolean empty = true;
        for (JsonValue entry : this) {
            if (!empty) {
                formattedString.append(",\n");
            } else {
                empty = false;
            }
            formattedString.append(entry.toString());
        }
        return "[\n" + Printer.indentString(formattedString.toString()) + "]";
    }
}
