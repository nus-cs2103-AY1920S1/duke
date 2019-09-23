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
        case CommandExit.COMMAND_WORD:
            return new CommandExit(detail);
        case CommandList.COMMAND_WORD:
            return new CommandList(detail);
        case CommandDelete.COMMAND_WORD:
            return new CommandDelete(detail);
        case CommandDone.COMMAND_WORD:
            return new CommandDone(detail);
        case CommandTodo.COMMAND_WORD:
            return new CommandTodo(detail);
        case CommandDeadline.COMMAND_WORD:
            return new CommandDeadline(detail);
        case CommandEvent.COMMAND_WORD:
            return new CommandEvent(detail);
        case CommandFind.COMMAND_WORD:
            return new CommandFind(detail);
        case CommandHi.COMMAND_WORD:
            return new CommandHi(detail);
        case CommandClear.COMMAND_WORD:
            return new CommandClear(detail);
        default:
            return new IllegalCommand(detail);
        }
    }

    private static String concatCommand(String[] strs) {
        assert strs != null;
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < strs.length; i++) {
            output.append(strs[i]).append(" ");
        }
        return output.toString().trim();
    }
}
