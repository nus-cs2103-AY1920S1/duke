package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.NoteList;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

/**
 * Represents all valid commands Duke will encounter.
 */
public abstract class Command {
    /** Indicates whether command is an exit command. */
    protected boolean isExit;

    /**
     * Creates and adds new task to list of tasks.
     * @param taskList TaskList agent to handle list of tasks.
     * @param ui Ui in charge of printing.
     * @param storageTasks Storage agent in charge of reading/writing of tasks to file.
     * @param storageNotes Storage agent in charge of reading/writing of notes to file.
     * @param noteList NoteList agent to handle list of notes.
     * @return any outputs/exceptions.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storageTasks, Storage storageNotes, NoteList noteList);

    /**
     * Returns whether command is an exit command.
     * @return true if is exit command, else false.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
