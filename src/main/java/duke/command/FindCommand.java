package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents an find command that Duke will operate.
 */
public class FindCommand implements Command {
    String keyword; //The keyword to be searched.

    /**
     * Initiates the object.
     *
     * @param keyword The keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns should the command exit.
     *
     * @return whether should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks  Tasks store in tasklist.
     * @param ui User interaction.
     * @param storage The storage area.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> tasklist = new ArrayList<String>();
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            String todo = String.format("%d. %s", i + 1, list.get(i).toString());
            if (todo.contains(keyword)) {
                tasklist.add(todo);
            }
        }

        ui.showFind(tasklist);
    }
}
