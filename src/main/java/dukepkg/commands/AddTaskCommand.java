package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;
import java.util.ArrayList;

public class AddTaskCommand extends Command {
    Task t;
    public AddTaskCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException {
        Parser.checkDuplicate(tasklist.tasks, t);
        tasklist.addTask(t);

        ui.showAddedTaskPrompt(tasklist.tasks, t);
        try {
            storage.saveList(tasklist.tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }
    }
}
