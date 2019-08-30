package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a List Command.
 * Each instance prints out the task list.
 */
public class ListCommand extends Command {

    /**
     * Creates an instance of List Command.
     * Sets isExit to false as it is not an exit command.
     */
    public ListCommand() {
        isExit = false;
    }

    /**
     * Prints the task list.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     * @return Returns a string of the response from duke after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
//        System.out.println(taskList);
        return taskList.toString();
    }
}
