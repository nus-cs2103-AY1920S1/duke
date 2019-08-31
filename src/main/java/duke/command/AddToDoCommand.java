package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class AddToDoCommand extends Command {

    private Task task;

    public AddToDoCommand(String[] commandArray) {
        String taskLine = "";
        for (int i = 1; i < commandArray.length; i++) {
            taskLine += " " + commandArray[i];
        }
        this.task = new Task(taskLine, false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getSize());
        try {
            storage.writeToFile(task.toFile());
        } catch (IOException e) {
            ui.showIOException(e);
        }
    }
}
