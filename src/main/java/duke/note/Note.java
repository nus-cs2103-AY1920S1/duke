package duke.note;

public class Note {
    public static final String FILE_BASE_PATH = ".\\src\\main\\java\\duke\\note\\notes\\";
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
