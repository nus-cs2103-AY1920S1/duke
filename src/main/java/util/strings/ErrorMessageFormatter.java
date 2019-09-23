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
        return "I'm sorry, I do not understand. "
                + message;
    }
}
