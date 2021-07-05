package dukepkg.commands;

import dukepkg.*;

/**
 * The command used to add a deadline task.
 */
class DeadlineCommand extends AddTaskCommand{
    /**
     * Instantiates a new Deadline command.
     *
     * @param t the deadline task that is going to be added by the command.
     */
    DeadlineCommand(Task t) {
        super(t);
    }
}
