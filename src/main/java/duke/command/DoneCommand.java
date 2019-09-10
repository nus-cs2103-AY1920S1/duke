package duke.command;

import duke.task.Task;

/**
 * Check the task base on the index given by the user.
 */
public class DoneCommand extends Command {
    /**
     * Mark the current task done.
     *
     * @param currTask refer to the specific task the user checks.
     * @return Duke's response to marking a task done.
     */
    public String taskComplete(Task currTask) {
        return printLine()
                + "     Nice! I've marked this task as done: \n       "
                + currTask + "\n" + printLine() + "\n";
    }

    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
