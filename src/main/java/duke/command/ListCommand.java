package duke.command;
import duke.ui.DukeUI;
import duke.tasklist.TaskList;
import duke.storagedata.StorageData;
public class ListCommand extends Command{
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        ui.printTasks(tasks);
    }
}