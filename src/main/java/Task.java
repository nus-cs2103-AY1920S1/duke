public class Task {
    protected String description;
    protected boolean isDone;
    protected char fileFormatType;
    protected String fileFormatDescription;
    protected DateTime fileFormatDateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() { return this.description; }

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

    public void changeToFileFormat(char type, String description, DateTime dateTime) {
        this.fileFormatType = type;
        this.fileFormatDescription = description;
        this.fileFormatDateTime = dateTime;
    }

    public String getFileFormat() {
        if (fileFormatType == 'T') {
            return fileFormatType + " | " + getStatusIcon() + " | " + fileFormatDescription;
        } else {
            return fileFormatType + " | " + getStatusIcon() + " | " + fileFormatDescription + " | " + fileFormatDateTime.toString();
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
