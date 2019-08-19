package main.java;

public class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    @Override
    protected String getTypeSymbol() {
        return "[T]";
    }

    @Override
    protected String getAdditionalInfo() {
        return "";
    }
}
