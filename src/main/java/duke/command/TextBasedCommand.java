package duke.command;

import duke.DukeException;
import duke.ui.ErrorMsgWithParams;

public abstract class TextBasedCommand extends Command {
    protected String line;
    protected String remainingLine;

    /**
     * Creates a text based command e.g. todo event deadline.
     *
     * @param line entire input line by user
     * @param command extracted command
     * @param lineDescriptor name of the description e.g. "description" or "search phrase"
     * @throws DukeException generic exception with error message
     */
    public TextBasedCommand(String line, String command, String lineDescriptor) throws DukeException {
        this.line = line;
        if (!line.contains(" ")) {
            if (this instanceof FindCommand) {
                throw new DukeException(ErrorMsgWithParams.EMPTY_DESCRIPT, command, lineDescriptor);
            } else {
                throw new DukeException(ErrorMsgWithParams.EMPTY_DESCRIPT, command,  lineDescriptor);
            }
        }
        remainingLine = line.replaceFirst(command + " ","");
        if (remainingLine.length() <= 0) {
            throw new DukeException(ErrorMsgWithParams.EMPTY_DESCRIPT, command,  lineDescriptor);
        }
    }
}
