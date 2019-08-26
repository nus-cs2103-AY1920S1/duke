package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Find Command.
 * Each instance prints out the task list of tasks that matches a string.
 */
public class FindCommand extends Command {
    /** Substring of find command. */
    private String substring;

    /**
     * Creates an instance of Find Command.
     * Sets isExit to false as it is not an exit command.
     */
    public FindCommand(String substring) {
        this.substring = substring;
        isExit = false;
    }

    /**
     * Prints out all tasks whose description contains substring.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTaskList = taskList.filterByString(substring);
        System.out.println(filteredTaskList);
    }
}
