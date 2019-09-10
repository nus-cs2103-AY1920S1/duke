package command;

import java.io.IOException;
import main.*;

public abstract class Command {
    public Command() {

    }

    public abstract void execute(TaskList tl, Storage st) throws IOException;
}
