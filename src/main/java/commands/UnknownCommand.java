package commands;

import logic.*;

public class UnknownCommand extends Command {
    /**
     * Overridden Method to throw an exception due to an unknown command
     *
     * @param tasks list of tasks
     * @param ui User Interface
     * @param storage File Storage and Management
     * @throws DukeException By default due to unknown command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }
}
