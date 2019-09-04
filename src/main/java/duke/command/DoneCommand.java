package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a "done" command.
 */
public class DoneCommand extends Command {
    /**
     * Constructor for command to mark a Task as done.
     *
     * @param taskId ID of task to be marked as done.
     */
    public DoneCommand(String taskId) {
        super(taskId);
    }

    /**
     * Marks Task associated with the taskId as done.
     * Ui then prints the Task that was marked as done.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     * @throws DukeException If taskId does not represent a valid number.
     */
    @Override
    public void executeCli(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(args);
        task.markAsDone();
        ui.printMarkTaskAsDoneMsg(task);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(args);
        task.markAsDone();
        return ui.getMarkTaskAsDoneMsg(task);
    }
}
