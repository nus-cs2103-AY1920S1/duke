package command;

import java.io.IOException;
import error.DukeException;
import task.Task;
import utils.Parser;
import utils.Ui;
import utils.Storage;
import utils.TaskList;
import utils.Alias;

/**
 * Deals with operation to add abbreviation.
 */
public class AddAlias extends Command {
    protected String command;

    public AddAlias(String command) {
        this.command = command;
    }

    /**
     * Executes operation to add abbrevation in static aliases.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] ar = command.split(" ");
            if (ar.length != 2) {
                throw new DukeException("OOPS!!! Add Synatx Command must be in this format: addsyntax <type> <alias>.");
            }
            Alias.updateAliases(ar[0].trim().toUpperCase(), ar[1].trim());
            return Ui.printAddAlias(ar[0].trim().toUpperCase(), ar[1].trim());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}