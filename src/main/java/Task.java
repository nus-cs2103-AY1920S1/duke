import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected String taskType;
    protected boolean isDone;
    protected LocalDateTime dateTime;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public Task(String description, String taskType, LocalDateTime dateTime, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
//        return (isDone ? "TASKDONE" : "TASKNOTDONE");
    }

    public String getDescription() {
        return description;
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

    public String getDateTime() {
        return dateTime.format(formatter);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String stringToPrint = getTaskType() + " " + getStatusIcon() + " " + getDescription();
        if(taskType.equals("deadline")) {
            return stringToPrint + " (by: " + getDateTime() + ")";
        } else if(taskType.equals("event")) {
            return stringToPrint + " (at: " + getDateTime() + ")";
        } else {
            return stringToPrint;
        }
    }
}
