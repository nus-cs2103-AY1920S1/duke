import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected Calendar time;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public TaskType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time.getTimeInMillis() + "";
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getTypeIcon() {
        String result = "";
        switch (type) {
            case TODO:
                result = "T";
                break;
            case DEADLINE:
                result = "D";
                break;
            case EVENT:
                result = "E";
                break;
        }

        return "[" + result + "]";
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String timeString = "";
        if (time != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
            timeString = formatter.format(time.getTime());
        }

        switch (type) {
            case TODO:
                return description;
            case DEADLINE:
                return description + " (by: " + timeString + ")";
            case EVENT:
                return description + " (at: " + timeString + ")";
            default:
                return description;
        }
    }
}