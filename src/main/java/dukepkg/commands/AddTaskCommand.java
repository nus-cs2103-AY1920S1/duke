package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;

/**
 * The type of command used to add tasks.
 */
public class AddTaskCommand extends Command {
    private final Task t;

    /**
     * Instantiates a new Add task command.
     *
     * @param t the task that is going to be added by the command.
     */
    AddTaskCommand(Task t) {
        this.t = t;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui) throws FormatException {
        Parser.checkDuplicate(TaskList.tasks, t);
        tasklist.addTask(t);

        String str = ui.showAddedTaskPrompt(TaskList.tasks, t);
        try {
            Storage.saveList(TaskList.tasks);
        } catch (IOException e) {
            str = ui.showSavingError(e);
        }
        return str;
    }
}
