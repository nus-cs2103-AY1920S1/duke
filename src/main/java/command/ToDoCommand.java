package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;
import task.ToDoTask;

/**
 * Represents a Command which adds an ToDoTask to the TaskList.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Creates a ToDoCommand with a given description.
     * @param description Task description.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a ToDoTask to the TaskList.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = new ToDoTask(description);
        tasks.addTask(task);
        storage.save(tasks);
        return "Got it. I've added this task:\n " + task + "\n" + "Now you have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.";
    }

    public boolean isExit() {
        return false;
    }

}
