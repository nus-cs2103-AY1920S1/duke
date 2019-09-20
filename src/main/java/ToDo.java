public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns string that displays the task in a readable format to the user.
     * @return string that displays the task in a readable format to the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string for storing in text file.
     * @return string for storing in text file
     */
    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}