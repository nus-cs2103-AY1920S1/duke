package duke.command;

import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

/**
 * A ListCommand contains instructions to list all existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates a new ListCommand, which requires no other details. The
     * superclass constructor is called with an empty string.
     */
    public ListCommand() {
        super("");
    }

    /**
     * Displays the current list of tasks on the given user interface, or
     * if the list is empty, displays an alternative message.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showText("You have no tasks now. Hooray!");
        } else {
            ui.showList(tasks);
        }
    }
}
