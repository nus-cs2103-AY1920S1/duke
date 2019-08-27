package duke.command;
import duke.ui.DukeUI;
import duke.tasklist.TaskList;
import duke.storagedata.StorageData;
import duke.exception.DukeTaskDoneException;
public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(String details) {
        super(details);
        this.taskNumber = Integer.valueOf(details);
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        try {
            tasks.done(this.taskNumber);
            ui.printTaskDoneMessage(tasks, this.taskNumber);
            storage.markTaskDoneInData(this.taskNumber);
        } catch (DukeTaskDoneException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS!!! Invalid Task Number detected.");
        }
    }
}
