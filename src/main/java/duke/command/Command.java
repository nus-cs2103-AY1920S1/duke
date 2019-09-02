package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    /**
     * Executes the current command.
     *
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @param taskList TaskList object.
     */
    public abstract String execute(Ui ui, Storage storage, TaskList taskList);

    /**
     * Returns if commands should continue being read.
     *
     * @return Boolean value if commands should continue being read.
     */
    public boolean shouldContinue() {
        return true;
    }
}
