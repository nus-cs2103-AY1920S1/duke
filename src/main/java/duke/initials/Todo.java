package duke.initials;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string that represents deadline that can be input into the txt file.
     * This method transforms the task into the specific format for the txt file
     * @return the data for this task
     */
    public String getData() {
        return "T" + "|" +
                (isDone ? "1" : "0") + "| " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
