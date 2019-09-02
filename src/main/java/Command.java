package duke.command;
import duke.error.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.util.ArrayList;

/**
 * abstract class for command
 * */
public abstract class Command {
    protected String command;

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    abstract public boolean isExit();
}