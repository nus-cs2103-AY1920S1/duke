package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;

/**
 * This is the specific command to list out all tasks whose task name contains the targeting string s.
 */
public class FindCommand extends Command {

    private String target;

    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * Call the task list to look for tasks with the given target string fractions in the task name.
     * Call the user interface to
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The tasks we are looking for as an arranged string.
     * @throws DukeException if the target string fraction is empty, which is "".
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        if (target.equals("")) {
            throw new DukeException("The finding message cannot be empty");
        }
        return ui.showFindMessage(tl.listMatchTask(target));
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
