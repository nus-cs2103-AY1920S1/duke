package duke.command;

import duke.task.Task;

/**
 * Delete the current task indicated by the user.
 */
public class DeleteCommand extends Command {
    /**
     * Delete the specific task from the task list prompted by the user.
     *
     * @param size takes in the size of the task list.
     * @param currTask refer to the specific task user wants to delete.
     * @return Duke's reply in response to deleting the task.
     */
    public String deleteComplete(int size, Task currTask) {
        return (printLine()
                + "     Noted. I've removed this task: \n       "
                + currTask
                + "\n     Now you have " + (size - 1) + " tasks in the list.\n"
                + printLine()
                + "\n");
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
