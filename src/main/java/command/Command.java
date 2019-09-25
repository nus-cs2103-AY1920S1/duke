package command;

import java.io.IOException;

import main.Storage;
import main.TaskList;

public abstract class Command {
    public Command() {

    }

    public abstract void execute(TaskList tl, Storage st) throws IOException;
}
