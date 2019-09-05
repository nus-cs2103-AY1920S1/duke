package duke.tasklist;

import duke.tasklist.Task;

public class Todo extends Task {
    /**
     * Constructor method for a todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method to format deadline to be saved.
     */
    @Override
    public String saveString() {
        String saveString = "T | ";

        if (this.isCompleted()) {
            saveString += 1;
        } else {
            saveString += 0;
        }
        saveString += " | ";
        saveString += this.getDescription();
        saveString += "\n";

        return saveString;
    }

    /**
     * toString method of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
