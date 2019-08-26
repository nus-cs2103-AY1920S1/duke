package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    int argument;

    public DeleteCommand(int argument) {
        this.argument = argument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask = tasks.getTask(argument);
        ui.printMessage("Noted. I've removed this duke.task: \n  " + myTask + "\nNow you have " + Ui.pluralize("duke/task", tasks.getSize() - 1) + " in the list.");
        tasks.deleteTask(argument);
        storage.save(tasks);
    }
}
