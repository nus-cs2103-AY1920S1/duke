package seedu.duke.model.dto;

public class Task {
    protected String type;
    protected String description;
    protected String errorMsg;
    protected boolean isDone;

    /**
     * Creates an empty Task object.
     */
    public Task() {
        this.type = "";
        this.description = "";
        this.isDone = false;
        this.errorMsg = "";
    }

    /**
     * Creates a Task object with description.
     * @param description task description.
     */
    public Task(String description) {
        this.type = "";
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int status) {
        this.description = description;
        this.isDone = (status == 1 ? true : false);
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setIsDone(boolean isDone) {

        this.isDone = isDone;
    }

    public boolean getIsDone() {

        return isDone;
    }

    public void setDone(boolean done) {

        isDone = done;
    }

    public String getType() {

        return type;
    }

    public void markAsDone() {
        setDone(true);
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✘]");
    }

    public String toTextFileString() {
        int status = (isDone ? 1 : 0);
        return type + "," + status + "," + description;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
