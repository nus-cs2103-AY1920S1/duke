package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;

/**
 * The command used to mark a task as finished.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Instantiates a new Done command.
     *
     * @param index the index of the task that is going to be marked as finished in the task list.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui) throws FormatException {
        Parser.validateModifyExistingTaskCommandIndex(index, TaskList.tasks.size());
        TaskList.tasks.get(index).markDone();

        String str = ui.showTaskDonePrompt(TaskList.tasks.get(index));
        try {
            Storage.saveList(TaskList.tasks);
        } catch (IOException e) {
            str = ui.showSavingError(e);
        }
        return str;
    }
}
