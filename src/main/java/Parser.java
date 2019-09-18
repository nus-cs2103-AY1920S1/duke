import java.util.Arrays;

/**
 * Parses the user inputs.
 */
public class Parser {
    /**
     * Splits the user input into a string array by spaces, one word per index.
     * @param input The string representing the user input.
     * @return The string array of words split by spaces.
     */
    public static String[] parseUserInput(String input) {
        return input.split(" ");
    }

    /**
     * Joins a string array back into a single string.
     * @param strings The string array to be joined.
     * @return A string of the combined words in the string array.
     */
    static String joinStrings(String[] strings) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                s.append(strings[i]);
            } else {
                s.append(strings[i]).append(" ");
            }
        }
        return s.toString();
    }

    /**
     * Combines strings from a string array, and splits the combined string into two arrays with a provided
     * identifier as the delimiter.
     * @param params The string array to be split.
     * @param identifier The identifier to serve as the delimited.
     * @return A string array, with the first string being the words before the delimiter, and the second string being
     *      the words after the delimiter.
     */
    static String[] splitByIdentifier(String[] params, String identifier) {
        int split = 0;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(identifier)) {
                split = i;
            }
        }

        assert split > 0 : "Input does not have an identifier.";

        String taskDesc = joinStrings(Arrays.copyOfRange(params, 0, split));
        String taskDue = joinStrings(Arrays.copyOfRange(params, split + 1, params.length));
        return new String[]{taskDesc, taskDue};
    }

}
