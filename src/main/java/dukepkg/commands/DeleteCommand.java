package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException {
        Parser.validateModifyExistingTaskCommandIndex(index, tasklist.tasks.size());
        Task t = tasklist.tasks.get(index);
        tasklist.tasks.remove(index);
        ui.showTaskDeletedPrompt(t, tasklist.tasks.size());
        try {
            storage.saveList(tasklist.tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }
    }
}
