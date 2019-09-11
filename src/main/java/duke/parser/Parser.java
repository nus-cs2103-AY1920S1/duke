package duke.parser;

import duke.command.*;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the user input String into executable commands.
     *
     * @param cmd User input String.
     * @return new Command object encapsulating the user's command.
     */
    public static Command parse(String cmd) {
        String[] cmds = cmd.split(" ");
        String command = cmds[0];
        String detail = concatCommand(cmds);
        switch (command) {
        case "bye":
            return new CommandExit(detail);
        case "list":
            return new CommandList(detail);
        case "delete":
            return new CommandDelete(detail);
        case "done":
            return new CommandDone(detail);
        case "todo":
            return new CommandTodo(detail);
        case "deadline":
            return new CommandDeadline(detail);
        case "event":
            return new CommandEvent(detail);
        case "find":
            return new CommandFind(detail);
        case "hi":
            return new CommandHi(detail);
        case "clear":
            return new CommandClear(detail);
        default:
            return new IllegalCommand(detail);
        }
    }

    private static String concatCommand(String[] strs) {
        StringBuffer output = new StringBuffer("");
        for (int i = 1; i < strs.length; i++) {
            output.append(strs[i] + " ");
        }
        return output.toString().trim();
    }
}
