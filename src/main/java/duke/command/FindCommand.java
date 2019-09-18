package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * Represents a command which finds the Tasks in the task list which contains the same use input String.
 *
 * @see TaskList
 * @see Task
 */
public class FindCommand extends Command {
    private final String keywordToFind;

    /**
     * Constructor for duke.command.FindCommand.
     *
     * @param commandSplitBySpaces String representation of the user input
     */
    public FindCommand(String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
        keywordToFind = commandSplitBySpaces[1];
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
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().contains(keywordToFind)) {
                tasks.add(t);
            }
        }
        return ui.printFindMessage(tasks);
    }
}
