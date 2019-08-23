/**
 * Factory Class for Tasks.
 */
public class TaskFactory {
    /**
     * Creates a Task object. Determines which subClass constructor to call.
     * @param description String description of the task to be created.
     * @return Task Object.
     */
    public static Task create(String description) throws TaskException {
        String[] tokens = description.split("\\s+");
        Command cmd = Command.lookup(tokens[0]);
        if (cmd == null) {
            throw new TaskException();
        }
        switch (Command.lookup(tokens[0])) {
        case TODO:
            return createToDo(description);
        case DEADLINE:
            return createDeadline(description);
        case EVENT:
            return createEvent(description);
        default:
            throw new TaskException();
        }
    }

    /**
     * Creates a ToDo Task Object.
     * @param description String description for task.
     * @return Task object.
     */
    private static Task createToDo(String description) throws ToDoTaskException {
        String[] tokens = description.trim().split("todo");
        if (tokens.length <= 1) {
            throw new ToDoTaskException();
        }
        String message = tokens[1];
        return new ToDo(message.trim());
    }

    /**
     * Creates a Deadline Task Object.
     * @param description String description for task.
     * @return Task Object.
     */
    private static Task createDeadline(String description) throws DeadlineTaskException {
        String[] tokens = description.trim().split("deadline");
        if (tokens.length <= 1) {
            throw new DeadlineTaskException();
        }
        String message = tokens[1];
        String[] divide = message.split("/by");
        return new Deadline(divide[0].trim(), divide[1].trim());
    }

    /**
     * Creates Event Task Object.
     * @param description String description for task.
     * @return Task Object.
     */
    private static Task createEvent(String description) throws EventTaskException {
        String[] tokens = description.trim().split("event");
        if (tokens.length <= 1) {
            throw new EventTaskException();
        }
        String message = tokens[1];
        String[] divide = message.split("/at");
        return new Event(divide[0].trim(), divide[1].trim());
    }

    /**
     * Creates Task Object.
     * @param description String description for task.
     * @return Task Object.
     */
    private static Task createTask(String description) {
        return new Task(description.trim());
    }

}
