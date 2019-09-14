package duke.Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand {

    public DeleteCommand(){

    }
    public void Delete(TaskList tasks, Ui ui, Storage store, String msg) throws IOException {
        int index = Integer.parseInt(msg);    //task to be deleted
        Task t = tasks.get_TaskList().get(index-1);
        tasks.remove(index);

        ui.print_delete(tasks.get_NoOfTasks(), t);
        store.AutoSave(tasks, tasks.get_NoOfTasks());
    }
}
