import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    protected LocalDateTime parseDateTime(String dateTime) {
        String[] parsedDateTime = dateTime.split(" ");
        String[] parsedDate = parsedDateTime[0].split("\\/");
        return LocalDateTime.of(
                Integer.parseInt(parsedDate[2]),
                Integer.parseInt(parsedDate[1]),
                Integer.parseInt(parsedDate[0]),
                Integer.parseInt(parsedDateTime[1]) / 100,
                Integer.parseInt(parsedDateTime[1]) % 100);
    }

    @Override
    public String toString() {
        return description;
    }

}
