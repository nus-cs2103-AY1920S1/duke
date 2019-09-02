package command;

import parser.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Tells user that the program is terminating.
 */
public class ExitCommand extends Command {
    /**
     * Instructs the program that it is terminating.
     *
     * @return true to indicate program is terminating
     */
    @Override
    public boolean canEnd() {
        return true;
    }

    /**
     * Prints exit message.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
