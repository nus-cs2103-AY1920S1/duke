package dukepkg.commands;

import dukepkg.Task;

/**
 * The command used to add a event into the task list.
 */
class EventCommand extends AddTaskCommand {
    /**
     * Instantiates a new Event command.
     *
     * @param t the event task that is going to be added by the command.
     */
    EventCommand(Task t) {
        super(t);
    }
}
