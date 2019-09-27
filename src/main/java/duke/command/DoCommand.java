package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents of a Do Command.
 * Each instance is a command to do a task.
 */
public class DoCommand extends Command {
    /** Index of task to do. */
    private int index;

    /**
     * Creates an instance of a Do Command.
     * Sets isExit to false as it is not an exit command.
     *
     * @param index Index of the task to do.
     */
    public DoCommand(int index) {
        this.index = index;
        isExit = false;
    }

    /**
     * Does a task in the task list, which is determined by a 1-based index.
     * Prints the response.
     * Writes to the data file.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     * @return Returns a string of the response from duke after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.doTask(index);
        String indent = ui.getIndent();
        String message = indent + "Nice! I've marked this task as done:\n" + indent + "  " + taskList.getTask(index);
        storage.write(taskList);
        return message;
    }
}
