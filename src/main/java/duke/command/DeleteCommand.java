package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a task in the list of tasks.
 */
public class DeleteCommand extends Command {
    /** Index of task user wants to delete. */
    String userIn;

    private static final String MESSAGE_SUCCESS =  "Noted. I've removed this task: \n"
            + "%s" + "\n" + "Now you have %d task(s) in the list.";

    private static final String MESSAGE_INVALID_NUM = "Invalid task number entered";

    /**
     * Initializes a DeleteCommand with the user input.
     * @param userIn index of task user wants to delete.
     */
    public DeleteCommand(String userIn) {
        this.userIn = userIn.trim();
    }

    /**
     * Deletes a specified task requested by the user.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     * @throws DukeException if index in user input is invalid.
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskInd = Integer.parseInt(userIn) - 1;
            Task delTask = tasks.getTask(taskInd);
            tasks.deleteTask(taskInd);
            int taskNum = tasks.getSize();
            storage.saveAll(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, delTask, taskNum));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_NUM);
        }
    }
}
