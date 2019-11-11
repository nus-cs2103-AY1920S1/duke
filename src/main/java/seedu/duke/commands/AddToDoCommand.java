package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.tasks.TaskManager;
import seedu.duke.tasks.ToDoTask;
import seedu.duke.tasks.exceptions.DuplicateTaskException;

import java.io.IOException;

public class AddToDoCommand extends AddTaskCommand {
    private String taskDescription;

    AddToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Add a <code>ToDoTask</code> task to the list of tasks that are stored both in memory and database file.
     * Afterwards, this methods triggers a notification to the user that a new <code>ToDoTask</code> task has
     * been successfully added to the task list.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return A <code>CommandResult</code> that contains a feedback to the user in the form of <code>String</code>.
     * @throws CommandExecutionException An error that occurs during command execution
     *                                   (e.g. due to input/output failure when updating database file).
     */
    @Override
    public CommandResult execute(TaskManager taskManager) throws CommandExecutionException {
        ToDoTask todo = new ToDoTask(taskDescription);
        try {
            int oldNumberOfTasks = taskManager.getNumberOfTasks();
            taskManager.addTask(todo);
            assert (oldNumberOfTasks + 1) == taskManager.getNumberOfTasks();

            return getCommandResult(todo, taskManager.getNumberOfTasks());
        } catch (DuplicateTaskException e) {
            throw new CommandExecutionException(e.getMessage());
        } catch (IOException e) {
            throw new CommandExecutionException(e.getMessage());
        } catch (Exception e) {
            throw new CommandExecutionException("Unknown error occurred while executing 'todo' command");
        }
    }
}
