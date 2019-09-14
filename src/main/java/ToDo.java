import java.time.format.DateTimeParseException;

public class ToDo extends Task {

    public ToDo(boolean done, String description) {
        super(description);
        this.isDone = done;
    }

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveFormat() {
        return String.format(" T | %d | %s\n", isDone ? 1 : 0, getDesc());
    }

    /**
     * Takes in an array of string, consist of space-split strings from a saved
     * input. Returns a ToDo object.
     * 
     * @param tokens an array of strings
     * @return an {@link Optional} {@link ToDo}.
     *
     * @throws NumberFormatException  if the number representing done is not 1 or 0
     * @throws DateTimeParseException if the date format is illegal
     */
    public static ToDo fromFormattedString(String[] tokens) throws NumberFormatException, DateTimeParseException {
        boolean done = Integer.parseInt(tokens[1]) == 1;
        ToDo todo = new ToDo(done, tokens[2]);
        return todo;
    }
}

