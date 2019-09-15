package util.command;

/**
 * Simple utility class to split user input into commands and arguments.
 */
public class CommandUtils {
    /**
     * Gets command from user input which is the first word.
     * @param userInput user input
     * @return command
     */
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    /**
     * Gets arguments from user input which is after the first word.
     * @param userInput user input
     * @return arguments
     */
    public static Arguments getArguments(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        if (splitInput.length > 1) {
            return new Arguments(splitInput[1]);
        } else {
            return new Arguments("");
        }
    }
}
