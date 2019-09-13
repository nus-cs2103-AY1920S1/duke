package duke.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand {

    public ToDoCommand(){

    }
    public void toDo(TaskList tasks, Ui ui, Storage storage, String msg) throws IOException {
        ui.print_toDo(msg, tasks.get_NoOfTasks());
        Task t = new Task(msg, 'T', 0, "");
        tasks.add(t);
        storage.AutoSave(tasks, tasks.get_NoOfTasks());
    }


}
