package duke.command;

import java.util.Arrays;

public class Parser {
    public static String[] parseUserInput(String input) {
        return input.split(" ");
    }

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

    static String[] splitByIdentifier(String[] params, String identifier) {
        int split = 0;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(identifier)) {
                split = i;
            }
        }
        String taskDesc = joinStrings(Arrays.copyOfRange(params, 0, split));
        String taskDue = joinStrings(Arrays.copyOfRange(params, split + 1, params.length));
        return new String[]{taskDesc, taskDue};
    }

}
