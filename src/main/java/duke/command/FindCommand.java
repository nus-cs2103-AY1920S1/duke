package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a "find" command.
 */
public class FindCommand extends Command {
    /**
     * Constructor for command to search for a task with description containing the searchString.
     *
     * @param searchString Keyword user is searching for.
     */
    public FindCommand(String searchString) {
        super(searchString);
    }

    /**
     * Search for task with description containing the searchString.
     * Ui then prints out the tasks containing the search keyword.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     */
    @Override
    public void executeCli(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tasksWithKeyword = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(args)) {
                tasksWithKeyword.add(task);
            }
        }

        ui.printFoundTasksMsg(tasksWithKeyword);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tasksWithKeyword = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(args)) {
                tasksWithKeyword.add(task);
            }
        }

        return ui.getFoundTasksMsg(tasksWithKeyword);
    }
}
