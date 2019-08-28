import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /***
     * Class constructor.
     * @param description Description of Task
     * @param type Type of Task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /***
     * Class constructor.
     * @param description Description of Task
     * @param type Type of Task
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public abstract String serialize();

    /***
     * Deserialize input String and return corresponding Task.
     * @param input Input string
     * @throws DukeException
     */
    static public Task deserialize(String input) throws DukeException {
        try {
            String[] parsedLine = input.split(" \\| ");
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
        catch (DateTimeParseException e) {
            throw new DukeException("Invalid File Format");
        }
    }

    /***
     *  Return a tick or cross depending on whether task is completed.
     */
    protected String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /***
     * Set task as complete.
     */
    protected void setDone() {
        isDone = true;
    }

    /***
     * Override toString method
     */
    @Override
    public String toString() {
        return description;
    }
}
