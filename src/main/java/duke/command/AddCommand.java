package duke.command;

import duke.task.TaskList;
import duke.task.TaskFactory;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.TaskException;

/**
 * Represents an Add Command.
 * Each instance is a command to add a task.
 */
public class AddCommand extends Command {
    /** Description of the task. */
    private String description;

    /**
     * Creates an instance of AddCommand.
     * Sets isExit to false as it is not an exit command.
     *
     * @param description Description of the task to be added.
     */
    public AddCommand(String description) {
        this.description = description;
        isExit = false;
    }

    /**
     * Adds the task to the task list.
     * Prints the response.
     * Writes the changes to the data file.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message = "";
        String indent = ui.getIndent();
        try {
            Task task = TaskFactory.create(description);
            Task.addToTotalTasks();
            taskList.addTask(task);
            message = task.message();
        } catch (TaskException e) {
            message = indent + e.getMessage();
        } finally {
            System.out.println(message);
            storage.write(taskList);
        }
    }
}
