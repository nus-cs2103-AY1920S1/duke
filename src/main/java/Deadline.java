public class Deadline extends Task {
    private static String DEADLINE_CMD = "deadline";
    private static String DEADLINE_PREFIX = "[D]";
    private static String DEADLINE_DATE_PREFIX = "(by: ";

    /**
     * Constructor.
     *
     * @param s Input String specifying task
     */
    public Deadline(String s) {
        super(s);
    }

    /**
     * Returns Task as formatted String.
     *
     * @return formatted String
     */
    public String toString() {
        return super.getString(super.toString(), DEADLINE_CMD, DEADLINE_PREFIX, DEADLINE_DATE_PREFIX);
    }

    public String toFileString() {
        return super.getString(super.toFileString(), DEADLINE_CMD, DEADLINE_PREFIX, DEADLINE_DATE_PREFIX);
    }





}
