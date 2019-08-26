package duke.storage;

import java.util.EnumMap;
import java.util.Map;

/**
 * Abstraction of a storage file parser responsible for resolving file input.
 */
abstract class StorageParser {
    static Map<StorageKey, String> parseJsonLine(String line)
            throws DukeTaskFileParseException {
        Map<StorageKey, String> lineMap = new EnumMap<StorageKey, String>(StorageKey.class);

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
}
