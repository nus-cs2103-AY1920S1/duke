package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;

/**
 * This is the Command subclass to list out all tasks in the target task list.
 * @Extends duke.commands.Command
 */
public class ListCommand extends Command {

    /**
     * Constructor of the class, nothing special.
     */
    public ListCommand() {

    }

    /**
     * The override method execute from the superclass Command.
     * It gets the string array list from the target tl and let the target ui to print it out.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     */
    @Override
    public String execute(TaskList tl, Ui ui) {
        checkNullPointer(tl, ui);
        return ui.showListMessage(tl.listAllTask());
    }

    /**
     * Determines whether this is an exit command.
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
