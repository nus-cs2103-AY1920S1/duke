package weijie.duke.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

    public static String indent(String input) {
        String indentation = "    ";
        return Arrays.stream(input.split("\n"))
                .map(line -> indentation + line)
                .collect(Collectors.joining("\n"));
    }

    public static String indentf(String input, Object... args) {
        String formattedInput = String.format(input, args);
        return indent(formattedInput);
    }
}
