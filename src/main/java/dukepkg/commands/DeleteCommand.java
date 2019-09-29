package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;

/**
 * The command used to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Instantiates a new Delete command.
     *
     * @param index the index of the task that is going to be deleted in the tasklist.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui) throws FormatException {
        Parser.validateModifyExistingTaskCommandIndex(index, TaskList.tasks.size());
        tasklist.deleteTask(index);
        Task t = TaskList.tasks.get(index);

        String str = ui.showTaskDeletedPrompt(t, TaskList.tasks.size());
        try {
            Storage.saveList(TaskList.tasks);
        } catch (IOException e) {
            str = ui.showSavingError(e);
        }
        return str;
    }
}
