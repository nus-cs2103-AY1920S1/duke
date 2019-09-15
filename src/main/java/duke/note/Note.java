package duke.note;

public class Note {
    public static final String FILE_BASE_PATH = ".\\src\\main\\java\\duke\\note\\notes\\";
    private String filePath;
    private String noteContents;
    
    public Note(String filePath) {
        this.filePath = filePath;
    }
    
    public Note(String filePath, String noteContents) {
        this.filePath = filePath;
        this.noteContents = noteContents;
    }
    
    String getFilePath() {
        return Note.FILE_BASE_PATH + this.filePath + ".txt";
    }
    
    public String getFileName() {
        return this.filePath;
    }
    
    String getNoteContents() {
        return this.noteContents;
    }
}
