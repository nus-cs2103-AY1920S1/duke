package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.note.Note;
import duke.note.NoteStorage;

import java.io.IOException;

public class ReadNoteCommand extends Command {
    private Note note;
    
    public ReadNoteCommand(Note note) {
        this.note = note;
    }
    
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        NoteStorage noteStorage = new NoteStorage(this.note);
        return ui.showAfterReadingNote(this.note.getFileName(), noteStorage.loadSavedNote());
    }
}
