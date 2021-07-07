package cs2103t.duke.task;

public class Note {
    /** Content of note. */
    private String content;
    /** Whether note is general or bound to task. */
    private boolean isGeneral;
    /** Whether note is deleted virtually already. */
    private boolean isDeleted;

    private Note() {

    }

    public Note(String note) {
        this.isGeneral = true;
        this.content = note;
        this.isDeleted = false;
    }

    public Note(String note, boolean isBound) {
        this.content = note;
        this.isGeneral = !isBound;
        this.isDeleted = false;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public String getContent() {
        return content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted() {
        isDeleted = true;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
