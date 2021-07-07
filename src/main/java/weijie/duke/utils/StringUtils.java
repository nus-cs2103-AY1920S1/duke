package weijie.duke.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

    /**
     * <p>
     *     Indents each line in the input with 4 spaces.
     * </p>
     * @param input A string to be indented.
     * @return The string with each line indented with 4 spaces.
     */
    public static String indent(String input) {
        String indentation = "    ";
        return Arrays.stream(input.split("\n"))
                .map(line -> indentation + line)
                .collect(Collectors.joining("\n"));
    }

    static String indentf(String input, Object... args) {
        String formattedInput = String.format(input, args);
        return indent(formattedInput);
    }
}
