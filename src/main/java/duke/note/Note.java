package duke.note;

import java.io.File;

public class Note {
    public static final String FILE_BASE_PATH = ".\\notes\\";
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
