package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a DeadlineCommand object in charge of adding a deadline task into the task list.
 */

public class DeadlineCommand extends Command {

    private final String activity;
    private final String deadline;

    /**
     * Creates a DeadlineCommand object.
     * @param fullCommand String of a full, valid command.
     * @param activity String of activity name.
     * @param deadline String of deadline.
     */
    public DeadlineCommand(String fullCommand, String activity, String deadline) {
        super(fullCommand);
        this.activity = activity;
        this.deadline = deadline;
    }

    @Override
    /**
     * Adds a deadline task into the taskList and updates file in storage.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     * @return String of duke's response message.
     * @throws DukeException when storage file is not found.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String message = tasks.addDeadline(activity, deadline, false);
        storage.updateFile(tasks);
        return message;
    }
}