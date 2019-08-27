package myduke.command;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;

public abstract class ModifyListCommand extends Command {
    protected final int itemIndex;

    /**
     * Constructs a modify List command.
     * @param itemIndex An item index.
     * @throws DukeException if the index is lesser than one.
     */
    public ModifyListCommand(int itemIndex) throws DukeException {
        this.itemIndex = itemIndex;

        if (this.itemIndex < 1) {
            throw new DukeInvalidCommandException("Invalid task reference number; index starts from 1");
        }
    }

    /**
     * Constructs a modify List Command.
     * @param itemIndex A String representing an item index.
     * @throws DukeException if the string is not an integer or the index is lesser than one.
     */
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
