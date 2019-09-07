package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

/**
 * Represents a command.
 * Parent class of all other types of commands.
 * A <code>Command</code> object corresponds to a command to perform some action on a <code>TaskList</code>.
 */
public abstract class Command {

    /**
     * Performs an action corresponding to the type of command.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    protected void addTags(Task task, String tags) throws MissingDescriptionException {
        boolean hasNoTag = tags.length() == 0;
        if (hasNoTag) {
            throw new MissingDescriptionException("tag");
        }
        String[] individualTags = tags.split("#");
        for (int i = 0; i < individualTags.length; i++) {
            boolean isEmptyTag = individualTags[i].trim().length() == 0;
            if (isEmptyTag) {
                continue;
            }
            task.addTag(individualTags[i].trim());
        }
    }
}
