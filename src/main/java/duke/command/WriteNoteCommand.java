package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.note.Note;
import duke.note.NoteStorage;

import java.io.IOException;

public class WriteNoteCommand extends Command {
    private Note note;
    
    public WriteNoteCommand(Note note) {
        this.note = note;
    }
    
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        new NoteStorage(this.note).writeSavedNote();
        return ui.showAfterWritingNote(this.note.getFileName());
    }
}
