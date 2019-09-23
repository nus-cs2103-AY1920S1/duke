package util.strings;

/**
 * Utility class to help split user input into the command keyword and the remaining arguments.
 */
public class CommandSplitter {
    /**
     * Isolates the first word of the user input and returns it as a the command keyword.
     * @param input the user's input.
     * @return the command keyword.
     */
    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Removes the first word and returns the rest of the String as the arguments for the command.
     * @param input the user's input.
     * @return the command's arguments.
     */
    public static String getArguments(String input) {
        String[] inputArray = input.split(" ", 2);

        if (inputArray.length < 2) {
            return "";
        }

        return inputArray[1];
    }
}
