package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.note.Note;
import duke.note.NoteStorage;

public class DeleteNoteCommand extends Command {
    private Note note;
    
    public DeleteNoteCommand(Note note) {
        this.note = note;
    }
    
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        new NoteStorage(this.note).deleteSavedNote();
        return ui.showAfterDeletingNote(this.note.getFileName());
    }
}
