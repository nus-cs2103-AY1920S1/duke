package dukepkg.commands;

import dukepkg.Task;

/**
 * The command used to add a todo command.
 */
class TodoCommand extends AddTaskCommand {
    /**
     * Instantiates a new Todo command.
     *
     * @param t the todo task that is going to be added by the command.
     */
    TodoCommand(Task t) {
        super(t);
    }
}
