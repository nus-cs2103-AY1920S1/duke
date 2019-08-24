import java.io.Serializable;

import java.time.format.DateTimeFormatter;

class Task implements Serializable{
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2717";

    private String task;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[" + TICK + "] " + this.task;
        } else {
            return "[" + CROSS + "] " + this.task;
        }
    }
}