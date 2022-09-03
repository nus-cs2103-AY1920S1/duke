package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;

/**
 * This is the specific command to list out all tasks in the target task list.
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Call the task list to list out every task information in it.
     * Call the user interface to process the task information and return them as a string.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The list of task information in a string.
     */
    @Override
    public String execute(TaskList tl, Ui ui) {
        checkNullPointer(tl, ui);
        return ui.showListMessage(tl.listAllTask());
    }

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
