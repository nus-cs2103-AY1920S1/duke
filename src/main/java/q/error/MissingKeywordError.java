package q.error;

/**
 * When Keywords are missing.
 */
public class MissingKeywordError extends QException {

    /**
     * Represents keywords in commands.
     */
    public enum Keyword {
        NUMBER, BY, AT;

        /**
         * Gets a String representing the keyword.
         *
         * @param keyword The keyword.
         * @return A String representing the keyword.
         */
        public static String getKeywordString(Keyword keyword) {
            switch (keyword) {
            case BY:
                return " /by ";
            case AT:
                return " /at ";
            default:
                return null;
            }
        }
    }

    /**
     * Constructs a MissingKeywordError.
     * @param keyword The missing Keyword.
     */
    public MissingKeywordError(Keyword keyword) {
        super("I need the " + getKeywordString(keyword) + " to work.");
    }

    private static String getKeywordString(Keyword keyword) {
        switch (keyword) {
        case NUMBER:
            return "number of a task";
        case BY:
            return "keyword /by";
        case AT:
            return "keyword /at";
        default:
            return "";
        }
    }
}
