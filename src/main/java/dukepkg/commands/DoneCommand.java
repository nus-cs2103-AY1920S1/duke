package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;

public class DoneCommand extends Command {
    private final int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException {
        Parser.validateModifyExistingTaskCommandIndex(index, TaskList.tasks.size());
        TaskList.tasks.get(index).markDone();
        ui.showTaskDonePrompt(TaskList.tasks.get(index));
        try {
            Storage.saveList(TaskList.tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }
    }
}
