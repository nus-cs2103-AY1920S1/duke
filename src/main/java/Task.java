import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {

    static protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm");
    protected String description;
    protected boolean isDone;


    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    static Task from(String taskInfo) {
        String[] taskInfos = taskInfo.split("\\|");
        switch (taskInfos[0]) {
        case "T":
            return new Todo(taskInfos[2], Boolean.parseBoolean(taskInfos[1]));
        case "D":
            return new Deadline(taskInfos[2], LocalDateTime.parse(taskInfos[3]), Boolean.parseBoolean(taskInfos[1]));
        case "E":
            return new Event(taskInfos[2], LocalDateTime.parse(taskInfos[3]), LocalTime.parse(taskInfos[4]),
                    Boolean.parseBoolean(taskInfos[1]));
        default:
            break;
        }
        return new Task(taskInfos[2]);
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus () {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
