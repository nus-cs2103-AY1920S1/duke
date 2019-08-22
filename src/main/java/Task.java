public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected String time;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
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
        switch (type) {
            case TODO:
                return description;
            case DEADLINE:
                return description + " (by: " + time + ")";
            case EVENT:
                return description + " (at: " + time + ")";
            default:
                return description;
        }
    }
}