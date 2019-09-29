package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a TodoCommand object in charge of adding a todo task into the tasklist.
 */

public class TodoCommand extends Command {

    private final String activity;

    /**
     * Creates a TodoCommand object.
     * @param fullCommand String of a full, valid command.
     * @param activity String of activity name.
     */
    public TodoCommand(String fullCommand, String activity) {
        super(fullCommand);
        this.activity = activity;
    }

    @Override
    /**
     * Adds a todo task into the taskList and updates file in storage.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     * @return String of duke's response message.
     * @throws DukeException when storage file is not found.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String message = tasks.addTodo(activity, false);
        storage.updateFile(tasks);
        return message;
    }
}