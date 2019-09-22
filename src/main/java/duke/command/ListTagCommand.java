package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to list out all the tags created by the user.
 */
public class ListTagCommand extends Command {
    private static final String MESSAGE_LIST_TAG = "Here are all the tags: \n";

    /**
     * Lists out the tags created by the user.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(listTasks(tasks));
    }

    private String listTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_LIST_TAG);
        sb.append(tasks.getAllTags());
        return sb.toString();
    }
}
