/**
 * Factory Class for Tasks.
 */
public class TaskFactory {

    /**
     * Creates a Task object. Determines which subClass constructor to call.
     * @param description String description of the task to be created.
     * @return Task Object.
     */
    public static Task create(String description) {
        String[] tokens = description.split("\\s+");
        switch (tokens[0]) {
            case "todo":
                return createToDo(description);
            case "deadline":
                return createDeadline(description);
            case "event":
                return createEvent(description);
            default:
                return createTask(description);
        }
    }

    /**
     * Creates a ToDo Task Object.
     * @param description String description for task.
     * @return Task object.
     */
    private static Task createToDo(String description) {
        String message = description.trim().split("todo")[1];
        return new ToDo(message.trim());
    }

    /**
     * Creates a Deadline Task Object.
     * @param description String description for task.
     * @return Task Object.
     */
    private static Task createDeadline(String description) {
        String message = description.trim().split("deadline")[1];
        String[] divide = message.split("/by");
        return new Deadline(divide[0].trim(), divide[1].trim());
    }

    /**
     * Creates Event Task Object.
     * @param description String description for task.
     * @return Task Object.
     */
    private static Task createEvent(String description) {
        String message = description.trim().split("event")[1];
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
