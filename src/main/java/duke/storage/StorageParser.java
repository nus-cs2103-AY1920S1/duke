package duke.storage;

import java.util.EnumMap;
import java.util.Map;

/**
 * Abstraction of a storage file parser responsible for resolving file input.
 */
abstract class StorageParser {

    /**
     * Method for parsing a single line of input.
     * Returns a Map object that can be used
     * to retrieve the relavant task data.
     * In the current iteration of the app, each task is
     * stored in json format on a single line.
     *
     * @param line The input string line.
     * @return The Map(Key:StorageKey, Value:String) object backed by EnumMap.
     * @throws DukeTaskFileParseException If an invalid storage key is encountered
     *     or the string is not in the correct format.
     */
    static Map<StorageKey, String> parseJsonLine(String line)
            throws DukeTaskFileParseException {
        Map<StorageKey, String> lineMap = new EnumMap<StorageKey, String>(StorageKey.class);

        checkOutermostBrackets(line);
        char[] lineArr = line.toCharArray();

        //first word should be a key
        boolean isKey = true;

        StringBuilder currentKey = new StringBuilder();
        StringBuilder currentValue = new StringBuilder();

        for (char c : lineArr) {
            switch (c) {
            case '{':
                break;
            case ':':
                isKey = false;
                break;
            case ',':
            case '}':
                isKey = true;
                try {
                    StorageKey key = StorageKey.valueOf(currentKey.toString().trim());
                    lineMap.put(key, currentValue.toString().trim());
                    currentKey = new StringBuilder();
                    currentValue = new StringBuilder();
                } catch (IllegalArgumentException ex) {
                    throw new DukeTaskFileParseException(
                            "Invalid key found in storage file (line will be skipped)",
                            " \u2639 OOPS!!! I found an invalid storage key in your storage file,\n"
                                    + " I'll skip that line!\n"
                                    + String.format("   Invalid Key: \'%s\'", currentKey.toString()));
                }
                break;
            default:
                if (isKey) {
                    currentKey.append(c);
                } else {
                    currentValue.append(c);
                }
            }
        }

        return lineMap;
    }

    /**
     * Private utility method to validate the perimeter brackets of the input json string.
     *
     * @param line The input string.
     * @throws DukeTaskFileParseException If the string has no opening or closing bracket.
     */
    private static void checkOutermostBrackets(String line) throws DukeTaskFileParseException {
        String jsonLine = line.trim();
        int lineLength = jsonLine.length();

        boolean hasOpeningBracket = lineLength > 0 && jsonLine.charAt(0) == '{';

        if (!hasOpeningBracket) {
            throw new DukeTaskFileParseException(
                    "Missing opening bracket while parsing file line.",
                    " \u2639 OOPS!!! I found line without an opening bracket in your storage file,\n"
                            + " I'll skip that line!\n");
        }

        boolean hasClosingBracket = jsonLine.charAt(lineLength - 1) == '}';

        if (!hasClosingBracket) {
            throw new DukeTaskFileParseException(
                    "Missing closing bracket while parsing file line.",
                    " \u2639 OOPS!!! I found a line without an closing bracket in your storage file,\n"
                            + " I'll skip that line!\n");
        }
    }
}
