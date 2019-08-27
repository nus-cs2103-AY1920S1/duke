package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command {
    int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException {
        Parser.validateModifyExistingTaskCommandIndex(index, tasklist.tasks.size());
        tasklist.tasks.get(index).markDone();
        ui.showTaskDonePrompt(tasklist.tasks.get(index));
        try {
            storage.saveList(tasklist.tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }
    }
}
