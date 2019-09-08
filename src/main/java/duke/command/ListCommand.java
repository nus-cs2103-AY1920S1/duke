package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a List Command.
 * Each instance prints out the task list.
 */
public class ListCommand extends Command {
    /** The type of tasks to be listed. */
    private String type;

    /**
     * Creates an instance of List Command.
     * Sets isExit to false as it is not an exit command.
     */
    public ListCommand() {
        type = "all";
        isExit = false;
    }

    /**
     * Creates an instance of List Command.
     * Sets isExit to false as it is not an exit command.
     */
    public ListCommand(String type) {
        this.type = type;
        isExit = false;
    }

    /**
     * Prints the task list.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     * @return Returns a string of the response from duke after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        switch (type) {
        case "todo":
            return taskList.filterByToDos().toString();
        case "deadline":
            return taskList.filterByDeadlines().toString();
        case "event":
            return taskList.filterByEvents().toString();
        case "all":
            return taskList.toString();
        default:
            return taskList.toString();
        }
    }
}
