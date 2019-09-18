package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a find command.
     *
     * @param keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param tasks to access the list of tasks
     * @param ui to give feedback to user
     * @throws DukeException when no matches are found
     */
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        return ui.showList(tasks.findMatch(this.keyword));
    }
}
