import java.lang.reflect.Array;

/**
 * Factory Class for Tasks. Responsible for logic determining the type of Task given the description.
 */
public class TaskFactory {
    /**
     * Creates a Task object. Determines which subClass constructor to call.
     * @param description String description of the task to be created.
     * @return Task Object.
     * @throws TaskException When task cannot be created. Sub-classes of task
     * exception are called in for specificity.
     */
    public static Task create(String description) throws TaskException {
        String[] tokens = description.split("\\s+");
        if (tokens.length == 0 || tokens[0] == null) {
            throw new TaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        switch (tokens[0]) {
        case "todo":
            return createToDo(description);
        case "deadline":
            return createDeadline(description);
        case "event":
            return createEvent(description);
        default:
            throw new TaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Creates a ToDo Task Object.
     * @param description String description for task.
     * @return Task object.
     * @throws ToDoTaskException If formatting of tokens in description is invalid.
     */
    private static Task createToDo(String description) throws ToDoTaskException {
        try {
            String[] tokens = description.trim().split("todo");
            if (tokens.length <= 1) {
                throw new ToDoTaskException("OOPS!!! The description of a todo cannot be empty.");
            }
            String message = tokens[1];
            return new ToDo(message.trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ToDoTaskException("OOPS!!! The description of a todo is invalid.");
        }
    }

    /**
     * Creates a Deadline Task Object.
     * @param description String description for task.
     * @return Task Object.
     * @throws DeadlineTaskException If formatting of tokens in description is invalid.
     */
    private static Task createDeadline(String description) throws DeadlineTaskException {
        try {
            String[] tokens = description.trim().split("deadline");
            if (tokens.length <= 1) {
                throw new DeadlineTaskException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String message = tokens[1];
            String[] divide = message.split("/by");
            return new Deadline(divide[0].trim(), divide[1].trim());
        } catch (InvalidDateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DeadlineTaskException("OOPS!!! The description of a deadline is invalid.");
        }
    }

    /**
     * Creates Event Task Object.
     * @param description String description for task.
     * @return Task Object.
     * @throws EventTaskException If formatting of tokens in description is invalid.
     */
    private static Task createEvent(String description) throws EventTaskException {
        try {
            String[] tokens = description.trim().split("event");
            if (tokens.length <= 1) {
                throw new EventTaskException("OOPS!!! The description of a event cannot be empty.");
            }
            String message = tokens[1];
            String[] divide = message.split("/at");
            return new Event(divide[0].trim(), divide[1].trim());
        } catch (InvalidDateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new EventTaskException("OOPS!!! The description of a event is invalid.");
        }
    }
}
