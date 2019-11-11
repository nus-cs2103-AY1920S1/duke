package task;

public class Todo extends Task {

    /**
     * Constructor for Todo task
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the string to how it should be saved in the .txt file
     * @return String to save in the .txt file
     */
    @Override
    public String storageString() {
        return "T/" + status + "/" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
