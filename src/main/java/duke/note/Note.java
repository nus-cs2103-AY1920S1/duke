package duke.note;

public class Note {
    private String fileBasePath = ".\\src\\main\\java\\duke\\note\\notes\\";
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
        return this.fileBasePath + this.filePath + ".txt";
    }
    
    public String getFileName() {
        return this.filePath;
    }
    
    String getNoteContents() {
        return this.noteContents;
    }
}
