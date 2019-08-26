package duke.command;

import duke.DukeUtil;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.TaskUtil;
import duke.task.TodoTask;

/**
 * Todo command that adds a todo task to the task list.
 */
class TodoCommand extends AddTaskCommand {
    /**
     * Constructor of the Todo command.
     * Calls its parent constructor then sets its commandType enum.
     *
     * @param commandArgs String array of arguments.
     */
    public TodoCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.todo;
    }

    /**
     * Checks whether the provided description is valid.
     * If valid, it then sets the taskToAdd instance variable,
     * for use in run.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If the description provided is empty.
     */
    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        String description = DukeUtil.concatStrings(commandArgs, " ");
        TaskUtil.validateTaskDescription(description);
        taskToAdd = new TodoTask(description);
    }
}
