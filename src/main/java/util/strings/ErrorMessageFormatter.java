package util.strings;

/**
 * Utility class to format error messages in a friendly manner.
 */
public class ErrorMessageFormatter {
    /**
     * Formats an error message in a friendly manner.
     * @param message the original error message.
     * @return the formatted message.
     */
    public static String formatErrorMessage(String message) {
        return "☹ OOPS! I'm sorry! "
                + message.replace(".", "!")
                + " :-(";
    }
}
