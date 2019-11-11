package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.tasks.EventTask;
import seedu.duke.tasks.TaskManager;
import seedu.duke.tasks.exceptions.DuplicateTaskException;

import java.io.IOException;

public class AddEventCommand extends AddTaskCommand {
    private String taskDescription;
    private String eventInfo;

    AddEventCommand(String taskDescription, String eventInfo) {
        this.taskDescription = taskDescription;
        this.eventInfo = eventInfo;
    }

    /**
     * Add an <code>EventTask</code> task to the list of tasks that are stored both in memory and database file.
     * Afterwards, this methods triggers a notification to the user that a new <code>EventTask</code> task has
     * been successfully added to the task list.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return A <code>CommandResult</code> that contains a feedback to the user in the form of <code>String</code>.
     * @throws CommandExecutionException An error that occurs during command execution
     *                                   (e.g. due to input/output failure when updating database file).
     */
    @Override
    public CommandResult execute(TaskManager taskManager) throws CommandExecutionException {
        EventTask eventTask = new EventTask(taskDescription, eventInfo);
        try {
            int oldNumberOfTasks = taskManager.getNumberOfTasks();
            taskManager.addTask(eventTask);
            assert (oldNumberOfTasks + 1) == taskManager.getNumberOfTasks();

            return getCommandResult(eventTask, taskManager.getNumberOfTasks());
        } catch (DuplicateTaskException | IOException e) {
            throw new CommandExecutionException(e.getMessage());
        } catch (Exception e) {
            throw new CommandExecutionException("Unknown error occurred while executing 'event' command");
        }
    }
}
