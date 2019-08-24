package duke.command;

import duke.DukeException;

public abstract class TextBasedCommand extends Command {
    protected String line;
    protected String remainingLine;

    public TextBasedCommand(String line, String command) throws DukeException {
        this.line = line;
        remainingLine = line.replaceFirst(command + " ","");
        if (remainingLine.length() <= 0) {
            throw new DukeException(command + " cannot have an empty description");
        }
    }
}
