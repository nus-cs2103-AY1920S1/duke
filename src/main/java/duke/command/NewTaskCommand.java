package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Abstract class that represents the creation and addition of a Task to the
 * current task list. Implements the execute() method for all
 * sub-classes that inherits from it. Inherits from the Command abstract
 * class.
 * @see Command
 */
public abstract class NewTaskCommand extends Command {
    private Task task;

    /**
     * Returns a NewTaskCommand that takes in a Task object to be added to the
     * list.
     * @param task Task object to be added to the current list
     */
    public NewTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the specified task to the current TaskList object.
     *
     * @return result feedback of the command to be printed to the user
     * @throws DukeException if list could not be saved to the hard disk
     *                       after addition.
     */
    @Override
    public String execute() throws DukeException {
        this.taskList.add(this.task);
        this.storage.saveToDisk(this.taskList);
        return "Got it. I've added this task:\n"
                + ui.indentMessage(task.toString())
                + "\nNow you have "
                + this.taskList.getSize()
                + " task(s) in the list.";
    }
}
