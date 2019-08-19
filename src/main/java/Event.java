package main.java;

public class Event extends Task {

    private String additionalInfo;

    Event(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[E]";
    }

    @Override
    protected String getAdditionalInfo() {
        return String.format("(at: %s)", additionalInfo);
    }
}
