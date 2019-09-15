package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.NoteList;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    /**
     * Constructs a list command.
     */
    public ListCommand() {
        super.isExit = false;
    }

    /**
     * Lists tasks.
     * @param tasks TaskList agent to handle list of tasks.
     * @param ui Ui in charge of printing.
     * @param storageTasks Storage agent in charge of writing to file.
     * @param storageNotes
     * @param noteList
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storageTasks, Storage storageNotes, NoteList noteList) {
        return tasks.listTasks(ui);
    }
}
