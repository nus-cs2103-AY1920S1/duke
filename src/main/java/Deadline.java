/**
 * Represents the deadline task given by the user.
 */
class Deadline extends TimedTask {

    Deadline(String taskDescription, String timestamp) throws DukeException {
        super(taskDescription, timestamp);
    }

    /**
     * Formats the timed task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the timed task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (isDone ? "\u2713" : "\u2717"),
                super.taskDescription, super.timestamp.format(TimedTask.dateTimeFormatter));
    }

}
