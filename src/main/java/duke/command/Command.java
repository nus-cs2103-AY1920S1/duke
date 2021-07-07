package duke.command;

import duke.storage.Storage;
import duke.task.PastOperationList;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Runs the specified command.
     *
     * @param taskList TaskList object for the duke program.
     * @param storage storage object for the duke program.
     * @param pastOperationList PastOperationList object for the duke program.
     * @return String to be printed.
     */
    public abstract String execute(TaskList taskList, Storage storage, PastOperationList pastOperationList);

    /**
     * Notifies the program to exit.
     *
     * @return true if it is exitCommand, else false.
     */
    public abstract boolean isExit();
}
