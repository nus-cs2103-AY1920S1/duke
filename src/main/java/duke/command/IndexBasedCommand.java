package duke.command;

import duke.DukeException;
import duke.command.Command;

public abstract class IndexBasedCommand extends Command {
    protected int index;

    /**
     * Create Index based command (e.g. done 1).
     * @param line line of input by user
     * @throws IndexOutOfBoundsException index out of bounds exception which may occur
     * @throws NumberFormatException may occur when parsing int from the string arr after splitting line
     * @throws DukeException generic exception with error message
     */
    public IndexBasedCommand(String line) throws IndexOutOfBoundsException, NumberFormatException, DukeException {
        String[] data = line.split(" ");
        if (data.length != 2) {
            throw new DukeException("Invalid number of arguments in an index based command");
        }
        index = Integer.parseInt(data[1].trim()) - 1;
    }
}
