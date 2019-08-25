package duke.task;

public class Event extends Task {

    private String additionalInfo;

    Event(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[E]";
    }

    @Override
    protected String displayAdditionalInfo() {
        return String.format("(at: %s)", additionalInfo);
    }
}
