package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;

public class AddTaskCommand extends Command {
    private final Task t;
    AddTaskCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException {
        Parser.checkDuplicate(TaskList.tasks, t);
        tasklist.addTask(t);

        ui.showAddedTaskPrompt(TaskList.tasks, t);
        try {
            Storage.saveList(TaskList.tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }
    }
}
