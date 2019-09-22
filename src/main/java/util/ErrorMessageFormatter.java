package util;

public class ErrorMessageFormatter {
    public static String formatErrorMessage(String message) {
        return "☹ OOPS! I'm sorry! " +
                message.replace(".", "!") +
                " :-(";
    }
}
