public class Deadlines extends Task {

    private String additionalInfo;

    Deadlines(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[D]";
    }

    @Override
    protected String getAdditionalInfo() {
        return String.format("(by: %s", additionalInfo);
    }

}
