package seedu.duke.commands;

import seedu.duke.tasks.Task;

abstract class AddTaskCommand extends Command {
    /**
     * Generates a <code>CommandResult</code> upon successful execution of <code>AddTaskCommand</code>.
     *
     * @param task          The new task that has been added to the list of tasks.
     * @param numberOfTasks Current number of tasks in the list.
     * @return A <code>CommandResult</code> that contains a feedback to the user in the form of <code>String</code>.
     */
    static CommandResult getCommandResult(Task task, int numberOfTasks) {
        return new CommandResult("Got it. I've added this task:\n" + "  " + task + "\n"
                + String.format("Now you have %d tasks in the list.\n", numberOfTasks));
    }
}
