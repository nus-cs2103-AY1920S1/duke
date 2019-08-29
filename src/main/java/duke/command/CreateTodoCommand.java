package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>todo taskName</code>
 */
public class CreateTodoCommand extends Command {
    public CreateTodoCommand(String commandInformation) {
        super(commandInformation);
    }

    /**
     * Executes commands in this format:
     * <code>todo taskName</code>
     * Reads result of executed command into preset task.txt file
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws DukeException if error related to Duke commands occurs
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String todoText = commandInformation;
        tasks.addTask(new Todo(todoText), true);
        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
