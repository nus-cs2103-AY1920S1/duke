package duke.note;

import java.io.File;

/**
 * A Note that stores information for the user.
 * Notes contain a title and a description.
 */
public class Note {
    /**
     * The directory location for writing Notes to the hard drive.
     */
    public static final String FILE_BASE_PATH = ".//notes//";
    
    /**
     * The File object that represents the directory location for writing Notes to the hard drive.
     */
    public static final File NOTE_DIRECTORY = new File(Note.FILE_BASE_PATH);
    
    private String fileName;
    
    private String noteContents;
    
    public Note(String fileName) {
        this.fileName = fileName;
    }
    
    public Note(String fileName, String noteContents) {
        this.fileName = fileName;
        this.noteContents = noteContents;
    }
    
    String getFilePath() {
        return Note.FILE_BASE_PATH + getFileName() + ".txt";
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    String getNoteContents() {
        return this.noteContents;
    }
}
