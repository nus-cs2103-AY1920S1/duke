import java.time.LocalDateTime;

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
    static public Task deserialize(String s) throws DukeException{
        String[] parsedLine = s.split(" \\| ");
        switch (parsedLine[0]) {
            case "T":
                return new Todo(parsedLine[2], Integer.parseInt(parsedLine[1]) == 1);
            case "D":
                return new Deadline(parsedLine[2], Integer.parseInt(parsedLine[1]) == 1, LocalDateTime.parse(parsedLine[3]));
            case "E":
                return new Event(parsedLine[2], Integer.parseInt(parsedLine[1]) == 1, LocalDateTime.parse(parsedLine[3]));
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


    @Override
    public String toString() {
        return description;
    }

}
