package duke.history;

import duke.command.UndoableCommand;

import java.util.Stack;

/**
 * Represents the history of commands executed by the user. Only tracks UndoableCommands
 * because commands like "list" don't change state.
 */
public class History {
    Stack<UndoableCommand> commands;

    public History() {
        commands = new Stack<>();
    }

    /**
     * Adds an executed command to the history stack.
     * @param command An executed command.
     */
    public void addExecutedCommand(UndoableCommand command) {
        commands.push((UndoableCommand) command);
    }

    /**
     * Gets an executed command from the history stack.
     * @return an executed command.
     */
    public UndoableCommand getExecutedCommand() {
        return commands.pop();
    }
}
