package duke.command;

import java.io.IOException;

public abstract class Command {
    protected abstract void execute() throws IOException;
}


