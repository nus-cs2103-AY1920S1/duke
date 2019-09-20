package duke.tasks;

import java.lang.StringBuilder;


/**
 * Represents a Todo task in the application.
 * A Todo provides a factory method to create itself.
 */
public class ToDo extends Task {

    /**
     * Initialises a todo task with the description of the task.
     *
     * @param description ToDo description
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }


    /**
     * Creates a ToDo task from the parameters provided for a ToDo task
     *
     * @return A ToDo task
     * @param tokens User input split by space
     */
    public static ToDo createToDo(String [] tokens) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < tokens.length - 1 ; i++) {
            builder.append(tokens[i]);
            builder.append(" ");
        }
        builder.append(tokens[tokens.length-1]);
        return new ToDo(builder.toString());
    }


    /**
     * Returns A string that includes the type task and the toString of Task.
     *
     * @return String that adds the type of the task to the toString method of Task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getDescription());
    }

}