public class Task {
    protected String description;
    protected int listIndex;
    protected String taskType;
    protected boolean isDone;
    protected String dateTime;

    public Task(String description, int listIndex, String taskType) {
        this.description = description;
        this.listIndex = listIndex;
        this.taskType = taskType;
        this.isDone = false;
    }

    public Task(String description, int listIndex, String taskType, String dateTime) {
        this.description = description;
        this.listIndex = listIndex;
        this.taskType = taskType;
        this.isDone = false;
        this.dateTime = dateTime;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return description;
    }

    public int getListIndex() {
        return listIndex;
    }

    public String getTaskType() {
        if(taskType.equals("todo")) {
            return "[T]";
        } else if(taskType.equals("deadline")) {
            return "[D]";
        } else if(taskType.equals("event")) {
            return "[E]";
        } else {
            return "";
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toStringWithIndex() {
        return getListIndex() +  ". " + getTaskType() + " " + getStatusIcon() + " " + getDescription();
    }

    @Override
    public String toString() {
        return getTaskType() + " " + getStatusIcon() + " " + getDescription();
    }
}
