package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    /**
     * * Task to be added.
     */
    private Task taskToAdd;

    /**
     * Constructor for AddCommand.
     *
     * @param taskToAdd the task to be added
     */
    public AddCommand(Task taskToAdd) {
        super();
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the command to add a task.
     * It adds the task to the TaskList provided by calling addTaskToList from
     * the TaskList class.
     *
     * @param tasks   The TaskList to be searched
     * @param ui      The ui to send messages to
     * @param storage Storage if required for the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTaskToList(this.taskToAdd);
        String answer = ("Got it. I've added this task:"
                + System.lineSeparator()
                + taskToAdd.getTaskStatus()
                + System.lineSeparator()
                + "Now you have " + tasks.getSize()
                        + ((tasks.getSize() == 1) ? " task" : " tasks") + " in the list.");
        ui.messageUser("Got it. I've added this task:",
                taskToAdd.getTaskStatus(),
                "Now you have " + tasks.getSize()
                        + ((tasks.getSize() == 1) ? " task" : " tasks") + " in the list.");
        return answer;
    }
}
