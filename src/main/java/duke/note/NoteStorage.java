package duke.note;

import duke.exception.ExistingNoteException;
import duke.exception.NoSuchNoteException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Writes and loads Note data to the hard drive.
 */
public class NoteStorage {
    private Note note;
    
    /**
     * Creates a NoteStorage object.
     *
     * @param note The file path of the stored data.
     */
    public NoteStorage(Note note) {
        this.note = note;
    }
    
    /**
     * Loads a saved Note as a String.
     *
     * @return Returns a String containing the contents of the Note.
     * @throws NoSuchNoteException A custom Exception indicating that the specified Note does not exist.
     * @throws IOException An Exception indicating that a specified file path does not exist.
     */
    public String loadSavedNote() throws NoSuchNoteException, IOException {
        File file = new File(this.note.getFilePath());
        if (!file.exists()) {
            throw new NoSuchNoteException("There is no existing note with the name " + this.note.getFileName() + "!");
        } else {
            String noteString = "";
            List<String> noteContents = Files.readAllLines(file.toPath());
            for (String string : noteContents) {
                noteString = noteString.concat(string);
            }
            return noteString;
        }
    }
    
    /**
     * Saves a Note to the hard drive.
     *
     * @throws ExistingNoteException A custom Exception indicating that the specified Note already exists.
     * @throws IOException An Exception indicating that a specified file path does not exist.
     */
    public void writeSavedNote() throws ExistingNoteException, IOException {
        File file = new File(this.note.getFilePath());
        if (file.exists()) {
            throw new ExistingNoteException("A note with the name '" + this.note.getFileName() + "' already exists!");
        } else {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(this.note.getFilePath());
            fileWriter.write(this.note.getNoteContents());
            fileWriter.close();
        }
    }
    
    /**
     * Deletes the specified Note.
     *
     * @throws NoSuchNoteException A custom Exception indicating that the specified Note does not exist.
     */
    public void deleteSavedNote() throws NoSuchNoteException {
        File file = new File(this.note.getFilePath());
        if (!file.exists()) {
            throw new NoSuchNoteException("There is no existing note with the name '" + this.note.getFileName()
                    + "'!");
        } else {
            file.delete();
        }
    }
}
