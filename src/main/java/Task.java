import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public Task(String description, TaskType type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    // Serialise
    public abstract String serialise();

    // Deserialize
    static public Task deserialize(String s) {
        String[] parsedLine = s.split(" \\| ");
        switch (parsedLine[0]) {
            case "T":
                return new Todo(parsedLine[2], Integer.parseInt(parsedLine[1]) == 1);
            case "D":
                return new Deadline(parsedLine[2], Integer.parseInt(parsedLine[1]) == 1, parsedLine[3]);
            case "E":
                return new Event(parsedLine[2], Integer.parseInt(parsedLine[1]) == 1, parsedLine[3]);
            default:
                return null;
        }
    }

    protected String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    protected void setDone() {
        isDone = true;
    }

    protected LocalDateTime parseDateTime(String dateTime) {
        // Check whether already in the right format
        try {
            return LocalDateTime.parse(dateTime);
        }
        catch (DateTimeParseException e) {}
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
