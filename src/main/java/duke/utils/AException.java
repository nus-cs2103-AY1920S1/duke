package duke.utils;

/**
 * Represents every exception that Duke may encounter
 * when the user uses Duke.
 */
public class AException {

    /**
     * Prints out message when the description of ToDo is empty.
     */
    public String emptyToDoException() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Prints out message when the description of Deadline is empty.
     */
    public String emptyDeadlineException() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }

    /**
     * Prints out message when the /by is not filled for Deadline.
     */
    public String emptyByException() {
        return "OOPS!!! The date and time of deadline not specified.";
    }

    /**
     * Prints out message when the description of Event is empty.
     */
    public String emptyEventException() {
        return"OOPS!!! The description of a event cannot be empty.";
    }

    /**
     * Prints out message when the /at is not filled for Event.
     */
    public String emptyAtException() {
        return "OOPS!!! The date and time of event not specified.";
    }

    /**
     * Prints out message when the command is not understood.
     */
    public String dontUnderstand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints out message when the user of Duke wants to access (delete/done)
     * a task that is not in the list.
     */
    public String exceedListSize() {
        return "OOPS!!! Task do not exist in list";
    }

    /**
     * Prints out message when the task has already been completed but the
     * user still issues the done command for that task.
     */
    public String taskAlreadyCompleted() {
        return "\tOOPS!!! Task has already been completed!";
    }
}