package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which lists out the Tasks in the task list.
 *
 * @see TaskList
 * @see Task
 */

public class ListCommand extends Command {
    /**
     * Constructor for duke.command.ListCommand.
     *
     * @param commandSplitBySpaces String representation of the user input
     */
    public ListCommand(String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
    }

    /**
     * Executes the command by using the three arguments provided.
     *
     * @param taskList taskList used to store tasks
     * @param ui User Interface
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getTasksSize() == 0) {
            throw new IllegalArgumentException("Nothing found in list");
        }
        String outputString = "";
        outputString = outputString + ui.printListMessage();
        outputString = outputString + ui.printTaskList(taskList);
        return outputString;
    }


}
