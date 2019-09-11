package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * Represents a command which finds the Tasks in the Tasklist which contains the same use input String.
 * @see TaskList
 * @see Task
 */
public class FindCommand extends Command{
    private String KeywordToFind;

    /**
     * Constructor for duke.command.FindCommand
     * @param commandSplitBySpaces String representation of the user input
     */
    public FindCommand(String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
        KeywordToFind = commandSplitBySpaces[1];
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().contains(KeywordToFind)) {
                tasks.add(t);
            }
        }
        return ui.printFindMessage(tasks);
    }
}
