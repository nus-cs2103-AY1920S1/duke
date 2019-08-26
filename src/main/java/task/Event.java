package task;

/**
 * <p>
 *     A Task that has a additional info component (that is usually
 *     a venue).
 * </p>
 */
public class Event extends Task {

    /**
     * <p>
     *     The additional info of an Event task. It is the content that is
     *     passed in after the "/at" keyword when interacting with the UI
     *     with "event" action.
     * </p>
     */
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
