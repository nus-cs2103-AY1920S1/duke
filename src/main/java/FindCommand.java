package duke.command;
import duke.error.DukeException;
import duke.task.Task;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import java.util.ArrayList;

public class FindCommand extends Command {
    protected String command;

    public FindCommand(String command) {
       this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> results = tasks.searchTasks(command);
            if (results.isEmpty()) {
                throw new DukeException("     0 Matching Results found!");
            } else {
                ui.printMatches(results);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}