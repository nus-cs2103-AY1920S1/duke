package command;

import exception.InvalidInstructionException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for handling invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Output invalid instruction exception.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     * @return Executed output as String.
     * @see InvalidInstructionException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInstructionException {
        throw new InvalidInstructionException();
    }
}
