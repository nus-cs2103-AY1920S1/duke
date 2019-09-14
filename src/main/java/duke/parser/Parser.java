package duke.parser;

import duke.command.Command;
import duke.command.CommandDelete;
import duke.command.CommandExit;
import duke.command.CommandList;
import duke.command.CommandDone;
import duke.command.CommandClear;
import duke.command.CommandHi;
import duke.command.CommandTodo;
import duke.command.CommandDeadline;
import duke.command.CommandEvent;
import duke.command.CommandFind;
import duke.command.IllegalCommand;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_HI = "hi";
    private static final String COMMAND_CLEAR = "clear";

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
        case COMMAND_BYE:
            return new CommandExit(detail);
        case COMMAND_LIST:
            return new CommandList(detail);
        case COMMAND_DELETE:
            return new CommandDelete(detail);
        case COMMAND_DONE:
            return new CommandDone(detail);
        case COMMAND_TODO:
            return new CommandTodo(detail);
        case COMMAND_DEADLINE:
            return new CommandDeadline(detail);
        case COMMAND_EVENT:
            return new CommandEvent(detail);
        case COMMAND_FIND:
            return new CommandFind(detail);
        case COMMAND_HI:
            return new CommandHi(detail);
        case COMMAND_CLEAR:
            return new CommandClear(detail);
        default:
            return new IllegalCommand(detail);
        }
    }

    private static String concatCommand(String[] strs) {
        assert strs != null;
        StringBuilder output = new StringBuilder("");
        for (int i = 1; i < strs.length; i++) {
            output.append(strs[i]).append(" ");
        }
        return output.toString().trim();
    }
}
