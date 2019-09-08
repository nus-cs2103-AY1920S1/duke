package duke.history;

import duke.command.Command;
import duke.command.UndoableCommand;

import java.util.Stack;


public class History {
    Stack<UndoableCommand> commands;

    public History() {
        commands = new Stack<>();
    }

    public void addExecutedCommand(UndoableCommand command) {
            commands.push((UndoableCommand) command);
    }

    public UndoableCommand getExecutedCommand() {
        return commands.pop();
    }
}
