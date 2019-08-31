package duke.command;

import java.text.ParseException;
import duke.task.*;
import duke.io.*;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ParseException;
}
