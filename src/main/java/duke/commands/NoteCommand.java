package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Note;
import duke.ui.UI;

import java.io.IOException;

/**
 * Note command.
 */
public class NoteCommand extends Command {
    Note note;

    /**
     * Initialise with Note.
     *
     * @param n note
     */
    public NoteCommand(Note n) {
        this.note = n;
    }

    /**
     * Adds Note to Task List and saves it in Storage.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     * @return Added To Do message or error message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addTask(note);
            storage.save(tasks.getTasks());
            return ui.getAddedMessage(taskMessage, tasks.getTasksSize());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Does not exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}