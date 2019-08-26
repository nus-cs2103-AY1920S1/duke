package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;
import task.ToDoTask;
import java.io.IOException;

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
     * @param ui Ui which feedbacks to user about success of command.
     * @param storage Storage which saves the task into the text file.
     * @throws DukeException DukeException that may arise from invalid inputs.
     * @throws IOException IOException if an I/O error occurs when writing onto the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = new ToDoTask(description);
        tasks.addTask(task);
        ui.print("Got it. I've added this task:\n " + task + "\n" + "Now you have " + tasks.getSize() + " task"
            + (tasks.getSize() == 1 ? " " : "s ") + "in the list.");
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
