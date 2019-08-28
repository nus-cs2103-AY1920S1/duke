package duke.parser;

import duke.command.Command;
import duke.command.CommandType;

public class Parser {
    public static Command parse(final String input) {
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0].toLowerCase();
        // If split.length == 1, there are no arguments
        String arguments = (split.length == 1) ? "" : split[1];
        CommandType type;
        switch (command) {
            case "list":
                type = CommandType.LIST;
                break;
            case "bye":
                type = CommandType.BYE;
                break;
            case "todo":
                type = CommandType.TODO;
                break;
            case "deadline":
                type = CommandType.DEADLINE;
                break;
            case "event":
                type = CommandType.EVENT;
                break;
            case "done":
                type = CommandType.DONE;
                break;
            case "find":
                type = CommandType.FIND;
                break;
            case "delete":
                type = CommandType.DELETE;
                break;
            default:
                type = CommandType.UNKNOWN;

        }
        return new Command(type, arguments);
    }
}
