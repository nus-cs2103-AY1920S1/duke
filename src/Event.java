public class Events extends Task {

    private String additionalInfo;

    Events(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[E]";
    }

    @Override
    protected String getAdditionalInfo() {
        return String.format("(by: %s", additionalInfo);
    }
}
