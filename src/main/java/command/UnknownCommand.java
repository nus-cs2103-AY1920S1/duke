package command;

import exception.UnknownCommandException;
import main.Storage;
import main.TaskList;

/**
 * A Command meant to handle the event in which an unknown command is given by the user.
 */
public class UnknownCommand implements Command {
    public UnknownCommand() {

    }

    /**
     * Prints into the console the error message when an unknown command is given, via the instantiation of an
     * UnknownCommandException.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public void execute(TaskList taskList, Storage storage) {
        System.err.println(new UnknownCommandException());
    }
}
