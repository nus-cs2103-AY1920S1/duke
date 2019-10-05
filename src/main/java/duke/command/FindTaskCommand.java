package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents the <code>Command</code> to mark a task as done from the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class FindTaskCommand extends Command {

    /**
     * Represents the keywords to search for from the tasklist.
     */
    protected String keywords;

    /**
     * Class constructor specifying the keywords to search for from the tasklist.
     *
     * @param keywords The keywords to be searched.
     */
    public FindTaskCommand(String keywords) {
        super();
        this.keywords = keywords;
    }

    /**
     * This method when called searches through <code>tasks</code> for the
     * keyword specified and prints out all tasks with the keyword.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ArrayList<Task> matchingTasks = tasks.findTaskWithWord(this.keywords);
            ui.printTaskList(matchingTasks);
        } catch (AssertionError e) {
            ui.printError(e.getMessage());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }
}
