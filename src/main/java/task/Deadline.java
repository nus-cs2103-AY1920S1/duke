package task;

/**
 * <p>
 *     A Task that has a deadline in the format DD/MM/YYYY HHmm
 *     as its additional info
 * </p>
 */
public class Deadline extends Task {

    private String additionalInfo;

    Deadline(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[D]";
    }

    @Override
    protected String displayAdditionalInfo() {
        return String.format("(by: %s)", additionalInfo);
    }

}
