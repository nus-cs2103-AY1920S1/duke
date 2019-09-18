package duke.command;

import duke.task.TaskList;

/**
 * A command that indicates that the user wants to exit application.
 */
public class ByeCommand extends Command {

    /**
     * Returns a string that says goodbye to the user.
     * @param tasks The TaskList of duke.
     * @return A string that says goodbye.
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }
}
