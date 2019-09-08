package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents an Exit Command.
 * Each instance is a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Creates an instance of an Exit Command.
     * Sets isExit to true as it is the exit command.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Exits Duke program.
     * Prints the response.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     * @return Returns a string of the response from duke after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String indent = ui.getIndent();
        String message = indent + "Bye. Hope to see you again soon!";
        return message;
    }
}
