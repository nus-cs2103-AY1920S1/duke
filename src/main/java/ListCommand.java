package duke.command;
import duke.error.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
/**
 * subclass of command
 * operation to print all list information
 * */
public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        ui.showList(tasks);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}