package util;

public class CommandUtils {
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    public static String getArguments(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        if (splitInput.length > 1) {
            return splitInput[1];
        } else {
            return "";
        }
    }
}
