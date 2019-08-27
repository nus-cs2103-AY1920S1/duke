import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }


    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return + symbols means done - symbol means not done
    }

    @Override
    public String toString() {
        String mark = "[" + getStatusIcon() + "] " + getDescription();
        return mark;
    }

    public String toDataString() {
        return isDone ? "1 | " + getDescription() : "0 | " + getDescription();
    }
    //...
}
