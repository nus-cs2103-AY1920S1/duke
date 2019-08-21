public class Task {

    protected String description;
    protected boolean isDone;
    protected String typeOfTask = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setTypeOfTask(String typeOfTask) {
        if (typeOfTask.equals("todo")) {
            this.typeOfTask = "T";
        } else if (typeOfTask.equals("event")) {
            this.typeOfTask = "E";
        } else {
            this.typeOfTask = "D";
        }
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public void addTime(String description) {
        this.description = new String(this.description + " " + description);
    }


    public String toString() {
        if (this.typeOfTask.equals("")) {
            return "[" + this.getStatusIcon() + "] " + this.description;
        } else {
            return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description;
        }
    }
}