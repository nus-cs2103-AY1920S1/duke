package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;


/**
 * Command to list all tasks containing keyword.
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword when executed, will search for all tasks with keyword.
     */
    public FindCommand(String keyword) {
        super.type = FullCommand.FIND;
        this.keyword = keyword;
    }


    /**
     * FindCommand is not a ExitCommand.
     * @return false.
     */

    public boolean isExit() {
        assert(!super.type.getName().equals("bye"));
        return false;
    }


    /**
     * Generates a TaskList shortlist containing only tasks with the keyword.
     * Passes TaskList as an argument for ui.showMatches to display to user.
     * @param tasks TaskList containing all Tasks.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException should not be thrown for this Command subclass.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> shortlist = new ArrayList<>();
        for (Task task: tasks.getList()) {
            if (task.findWord(keyword)) {
                shortlist.add(task);
            }
        }
        ui.showMatches(new TaskList(shortlist));
    }
}
