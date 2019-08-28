public class Task {
    protected String description;
    protected boolean isDone;
    protected char fileFormatType;
    protected String fileFormatDescription;
    protected String fileFormatDetail;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void changeToFileFormat(char type, String description) {
        this.fileFormatType = type;
        this.fileFormatDescription = description;

    }

    public void changeToFileFormat(char type, String description, String detail) {
        this.fileFormatType = type;
        this.fileFormatDescription = description;
        this.fileFormatDetail = detail;
    }

    public String getFileFormat() {
        if (fileFormatType == 'T') {
            return fileFormatType + " | " + getStatusIcon() + " | " + fileFormatDescription;
        } else {
            return fileFormatType + " | " + getStatusIcon() + " | " + fileFormatDescription + " | " + fileFormatDetail;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
