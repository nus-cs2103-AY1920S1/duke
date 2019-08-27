package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException {
        Parser.validateModifyExistingTaskCommandIndex(index, TaskList.tasks.size());
        Task t = TaskList.tasks.get(index);
        TaskList.tasks.remove(index);
        ui.showTaskDeletedPrompt(t, TaskList.tasks.size());
        try {
            Storage.saveList(TaskList.tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }
    }
}
