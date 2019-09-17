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
    public void execute(TaskList tasks, Ui ui, Saved file) throws DukeException {
        ui.printList(tasks.findMatch(this.keyword));
    }
}
