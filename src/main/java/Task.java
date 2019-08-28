public class Task {

    protected String description;
    protected boolean isDone;
    protected String typeOfTask = "";
    protected String time = "";

    public Task(String description, String typeOfTask) {
        this.description = description;
        if (typeOfTask.equals("todo")) {
            this.typeOfTask = "T";
        } else if (typeOfTask.equals("event")) {
            this.typeOfTask = "E";
        } else {
            this.typeOfTask = "D";
        }
        this.isDone = false;
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

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void addTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        if (!this.time.equals("")) {
            return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description + " " + this.time;
        } else {
            return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description;
        }
    }
}