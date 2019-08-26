package duke.command;

import duke.TaskList;
import duke.error.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand implements Command {
    private String keyword;
    
    /**
     * Constructor.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Check if exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int counter = 1;
        ui.prettyPrint4("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getName().contains(this.keyword)) {
                ui.prettyPrint4(String.format("%d.%s", counter, task));
                counter++;
            }
        }
    }
}
