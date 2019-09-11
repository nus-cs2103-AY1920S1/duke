package commands;

import java.io.IOException;
import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.util.Stack;

/**
 * Encapsulates a command manager to manage the running of commands by Duke.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 5
 */
public class CommandManager {
    /** Stack to keep track of user commands. */
    private Stack<Command> commandStack = new Stack<Command>();

    /**
     * Executes the given command.
     *
     * @param command the comand to be executed.
     * @param tasks the task list the task is to be added to.
     * @param ui the user interface associated with this run of Duke.
     * @param storage the storage handler associated with this run of Duke.
     * @return Duke's response to the user command.
     * @throws IOException when file the list is to be written to is not found.
     */
    public String executeCommand(Command command, TaskList tasks, Ui ui,
                               Storage storage) throws IOException {
        if (command instanceof UndoableCommand) {
            commandStack.push(command);
        }
        return command.execute(tasks, ui, storage);
    }

    /**
     * Undos the last executed command.
     *
     * @param tasks the task list the task is to be added to.
     * @param ui the user interface associated with this run of Duke.
     * @param storage the storage handler associated with this run of Duke.
     * @return Duke's response to the user command.
     * @throws IOException when file the list is to be written to is not found.
     */
    public String undoCommand(TaskList tasks, Ui ui, Storage storage)
            throws IOException {
        if (commandStack.isEmpty()) {
            return ui.showNoCommandToUndoError();
        } else {
            UndoableCommand command = (UndoableCommand)commandStack.pop();
            return command.undo(tasks, ui, storage);
        }
    }
}
