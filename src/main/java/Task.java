public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public  String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String type) {
        this.taskType = type;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + "[" + this.getStatusIcon() + "]" + " " + this.description);
    }

    public void setTime(String time) {
        this.time = time;
    }
}
