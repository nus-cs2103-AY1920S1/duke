package seedu.duke.commands;

import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Task;
import seedu.duke.ui.StringStore;

/**
 * Abstraction of the Add Command containing the required data to add a plain-old task.
 * Contains the {@code description} required to create a plain-old Task
 */
public class AddCommand extends Command {

    private String description;

    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a plain-old task with description {@code description} to the TaskList {@code tasks}.
     * @param tasks The TaskList object to add the task to.
     */
    @Override
    public String execute(TaskList tasks) {
        Task task = new Task(description);
        tasks.add(task);
        return StringStore.ADD_SUCCESSFUL + task.toString();
    }
}
