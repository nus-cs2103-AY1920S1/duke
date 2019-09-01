package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which lists out the Tasks in the Tasklist.
 * @see TaskList
 * @see Task
 */

public class ListCommand extends Command {
    /**
     * Constructor for duke.command.ListCommand
     * @param stringCommand String representation of the user input
     */
    public ListCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printListMessage();
        ui.printTaskList(taskList);
        taskList.list();
    }

    /**
     * Checks if duke.Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
