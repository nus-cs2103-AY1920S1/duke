package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {
    protected String details;
    protected boolean isExit;
    protected boolean isDone;

    Command(String details) {
        this.details = details;
        this.isExit = false;
        this.isDone = false;
    }

    /**
     * Returns the details associated with this command.
     *
     * @return  Command details
     */
    public String getDetails() {
        return details;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Executes the current command using the given resources.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If command details are invalid, etc.
     */
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException;

    /**
     * Saves the current task list in the file `[root]/data/duke.txt`.
     *
     * @throws DukeException    If file does not get written properly
     */
    void save(TaskList tasks, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException(
                    "oops! I encountered an error when saving your tasks.\n"
                            + "    " + e.getMessage() + "\n"
                            + "If you say bye now, you may not be able to access this\n"
                            + " list in future.");
        }
    }

    /**
     * Returns the taskList index of the task with the given number if such a
     * task exists, and throws an exception otherwise. Note that taskList is
     * zero-indexed, whereas the input number is one-indexed.
     *
     * @param number            String that should contain a number
     * @param numberOfTasks     Number of tasks in the list currently
     * @return                  The requested task index
     * @throws DukeException    Exception message indicating task not found
     */
    int getTaskIndex(String number, int numberOfTasks) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(number);
            if (taskIndex < 1 || taskIndex > numberOfTasks) {
                throw new DukeException("I couldn't find the task you requested!");
            }
            return taskIndex - 1; // taskList is zero-indexed
        } catch (NumberFormatException e) {
            throw new DukeException("I couldn't find the task you requested!");
        }
    }
}
