package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.DeadlineTask;
import task.Task;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private String description;
    private String time;

    public DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = new DeadlineTask(description, time);
        tasks.addTask(task);
        ui.print("Got it. I've added this task:\n " + task + "\n" + "Now you have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.");
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
