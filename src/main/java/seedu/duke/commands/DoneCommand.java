package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.commands.exceptions.InvalidCommandException;
import seedu.duke.tasks.TaskManager;

import java.io.IOException;

public class DoneCommand extends Command {
    private long taskNumber;

    DoneCommand(long taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a particular task as done in the list of tasks. Afterwards, this methods triggers a notification to the
     * user that a task has been marked as done.
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
            taskManager.markAsDone(taskNumber);
            assert oldNumberOfTasks == taskManager.getNumberOfTasks();

            return new CommandResult("Nice! I've marked this task as done:\n"
                    + "  " + taskManager.getTaskWithID(taskNumber) + "\n");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(e.getMessage());
        } catch (IOException e) {
            throw new CommandExecutionException(e.getMessage());
        } catch (Exception e) {
            throw new CommandExecutionException("Unknown error occurred while executing 'done' command");
        }
    }
}
