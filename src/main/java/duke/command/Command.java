package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

import java.text.ParseException;

/**
 * Abstract representation for Commands.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ParseException;
}
