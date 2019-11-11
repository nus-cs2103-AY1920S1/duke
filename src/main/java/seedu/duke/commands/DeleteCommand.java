package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.commands.exceptions.InvalidCommandException;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskManager;

import java.io.IOException;

public class DeleteCommand extends Command {
    private long taskNumber;

    DeleteCommand(long taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Removes a certain task from the list of tasks. Afterwards, this methods triggers a notification to the user
     * that a task has been successfully removed from the list.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return A <code>CommandResult</code> that contains a feedback to the user in the form of <code>String</code>.
     * @throws InvalidCommandException   An error that occurs if the user provides an invalid task number.
     * @throws CommandExecutionException An error that occurs during command execution
     *                                   (e.g. due to input/output failure when updating database file).
     */
    @Override
    public CommandResult execute(TaskManager taskManager) throws InvalidCommandException, CommandExecutionException {
        try {
            int oldNumberOfTasks = taskManager.getNumberOfTasks();
            Task removedTask = taskManager.removeTask(taskNumber);
            assert oldNumberOfTasks == (taskManager.getNumberOfTasks() + 1);

            return new CommandResult("Noted. I've removed this task:\n" + "  " + removedTask + "\n"
                    + String.format("Now you have %d tasks in the list.\n", taskManager.getNumberOfTasks()));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Task number does not exist");
        } catch (IOException e) {
            throw new CommandExecutionException(e.getMessage());
        } catch (Exception e) {
            throw new CommandExecutionException("Unknown error occurred while executing 'delete' command");
        }
    }
}
