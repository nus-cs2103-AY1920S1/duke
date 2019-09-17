public class Event extends Task {
    private static String EVENT_CMD = "event";
    private static String EVENT_PREFIX = "[E]";
    private static String EVENT_DATE_PREFIX = "(at: ";

    /**
     * Constructor.
     *
     * @param s Input String specifying task
     */
    public Event(String s) {
        super(s);
    }

    /**
     * returns Task as formatted String.
     *
     * @return formatted String
     */
    public String toString() {
        return super.getString(super.toString(), EVENT_CMD, EVENT_PREFIX, EVENT_DATE_PREFIX);
    }

    public String toFileString() {
        return super.getString(super.toFileString(), EVENT_CMD, EVENT_PREFIX, EVENT_DATE_PREFIX);
    }
}
