package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.ToDo;
import java.io.IOException;

public class ToDoCommand extends Command {
    ToDo td;

    public ToDoCommand(ToDo td) {
        this.td = td;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addToDo(td);
            ui.showAddedMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}