package myduke.command;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;

public abstract class ModifyListCommand extends Command {
    protected final int itemIndex;

    public ModifyListCommand(int itemIndex) throws DukeException {
        this.itemIndex = itemIndex;

        if (this.itemIndex < 1) {
            throw new DukeInvalidCommandException("Invalid task reference number; index starts from 1");
        }
    }

    public ModifyListCommand(String itemIndex) throws DukeException {
        try {
            this.itemIndex = Integer.parseInt(itemIndex);
        } catch (NumberFormatException ex) {
            throw new DukeInvalidCommandException("Task reference number must be an integer");
        }

        if (this.itemIndex < 1) {
            throw new DukeInvalidCommandException("Invalid task reference number; index starts from 1");
        }
    }
}
