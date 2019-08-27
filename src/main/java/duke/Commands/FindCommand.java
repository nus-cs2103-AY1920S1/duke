package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;

/**
 * This is the command subclass to list out all tasks whose task name contains the targeting string s
 * @Extends Command
 */
public class FindCommand extends Command{

    private String target;

    /**
     * Constructor of the class, nothing special.
     * @param target The target string to look for in the task names.
     */
    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * The override method execute from the superclass Command.
     * It gets the string array list from the target tl and let the target ui to print it out.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException If the target string is empty.
     */
    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException {
        if (target.equals("")) {
            throw new DukeException("The finding message cannot be empty");
        }
        ui.showFindMessage(tl.listMatchTask(target));
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
