package cs2103t.duke.command;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.file.Storage;
import cs2103t.duke.task.NoteList;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class ListGeneralNotesCommand extends Command {
    public ListGeneralNotesCommand() {
        super.isExit = false;
    }

    /**
     * Creates and adds new task to list of tasks.
     * @param tasks TaskList agent to handle list of tasks.
     * @param ui Ui in charge of printing.
     * @param storageTasks Storage agent in charge of writing to file.
     * @param storageNotes
     * @param noteList NoteList agent
     * @throws DukeException if command is invalid or cannot write to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storageTasks, Storage storageNotes, NoteList noteList) throws DukeException {
        return noteList.listGeneralNotes(ui);
    }
}
