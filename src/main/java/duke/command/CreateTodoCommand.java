package duke.command;

import duke.utilities.Storage;
import duke.task.TaskList;
import duke.ui.MessageHandler;
import duke.exception.DukeException;
import duke.task.Task;
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
     * @param messageHandler      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws DukeException if error related to Duke commands occurs
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage) throws DukeException {
        String todoText = commandInformation;
        Task t =  new Todo(todoText);
        tasks.addTask(t);
        String response = messageHandler.addTaskConfirmationMessage(t);

        storage.writeToTasksFile(tasks);

        return response;
    }

}
