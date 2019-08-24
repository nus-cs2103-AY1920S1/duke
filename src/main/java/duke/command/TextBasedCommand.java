package duke.command;

import duke.DukeException;

public abstract class TextBasedCommand extends Command {
    protected String line;
    protected String remainingLine;

    /**
     * Creates a text based command e.g. todo event deadline.
     * @param line entire input line by user
     * @param command extracted command
     * @throws DukeException generic exception with error message
     */
    public TextBasedCommand(String line, String command) throws DukeException {
        this.line = line;
        remainingLine = line.replaceFirst(command + " ","");
        if (remainingLine.length() <= 0) {
            throw new DukeException(command + " cannot have an empty description");
        }
    }
}
