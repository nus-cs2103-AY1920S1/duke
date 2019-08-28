package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command{
    private int taskNumberDone;

    public DoneCommand(String command, Task pending, int taskNumberDone){
        super(command, pending);

        this.taskNumberDone = taskNumberDone;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = list.setTaskDone(taskNumberDone - 1);
        storage.save(list.printList());
        ui.showDoneTask(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
