package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * This is a <code>Command</code> to indicate a termination of user input. This changes the status of
 * <code>isExit</code> status to be <code>true</code>, while for all other commands, the status is set to be
 * <code>false</code>.
 */
public class ExitCommand extends Command {

    /**
     * This is a class constructor to set <code>isExit</code> to be <code>true</code>.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Terminates the user input by changing <code>isExist</code>, the condition for loops, to be <code>true</code>.
     * Besides, it also save the whole list to hard disk for future use.
     *
     * @param taskList        {@inheritDoc}
     * @param ui              {@inheritDoc}
     * @param storage         {@inheritDoc}
     * @return                a string saying bye to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            return ui.showSavingError();
        }
        return ui.showGoodbye();
    }

}
