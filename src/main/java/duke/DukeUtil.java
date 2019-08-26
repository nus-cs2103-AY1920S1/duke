package duke;

/**
 * A class providing several common utility functions.
 */
public abstract class DukeUtil {
    /**
     * Concatenates an array of strings with a specified delimiter.
     *
     * @param strings Array of strings to concatenate.
     * @param delimiter Delimiter to use to join strings together.
     * @return Concatenated String.
     */
    public static String concatStrings(String[] strings, String delimiter) {
        if (strings.length == 0) {
            return "";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < strings.length - 1; i++) {
            output.append(strings[i]);
            output.append(delimiter);
        }
        output.append(strings[strings.length - 1]);

        return output.toString();
    }

    /**
     * Returns in the first index of a pattern in an array of strings.
     * Returns -1 if it is not found.
     *
     * @param strings Array of strings to search.
     * @param pattern Pattern string to search for.
     * @return Index of pattern in array, or -1 if not found.
     */
    public static int getIndexOfPattern(String[] strings, String pattern) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(pattern)) {
                return i;
            }
        }

        return -1;
    }
}
