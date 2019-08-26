package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.DeadlineTask;
import task.Task;
import java.io.IOException;

/**
 * Represents a Command which adds a DeadlineTask to the TaskList.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String time;

    /**
     * Creates a DeleteCommand with a given description and time.
     * @param description Task description.
     * @param time Task deadline date and time in the format of "dd/mm/yyyy hhmm".
     */
    public DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Adds a DeadlineTask to the TaskList.
     * @param tasks TaskList which stores the list of tasks.
     * @param ui Ui which feedbacks to user about success of command.
     * @param storage Storage which saves the task into the text file.
     * @throws DukeException DukeException that may arise from invalid inputs.
     * @throws IOException IOException if an I/O error occurs when writing onto the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = new DeadlineTask(description, time);
        tasks.addTask(task);
        ui.print("Got it. I've added this task:\n " + task + "\n" + "Now you have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.");
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
