package seedu.duke.cli;

import seedu.duke.tasks.Task;

import java.util.List;

/**
 * A command that performs actions on a task list.
 */
public interface Command {
    /**
     * Execute the command on the task list.
     *
     * @param taskList The task list to operate on
     * @throws CommandException if an error occurred while running the command
     */
    void execute(List<Task> taskList) throws CommandException;
}
