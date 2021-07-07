package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.exception.EmptyDescriptionException;
import java.io.IOException;
import java.text.ParseException;

/** The Command class represents commands made by the user that are yet to be executed. */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks Current {@link TaskList}
     * @param ui {@link Ui} being used
     * @param storage Current {@link Storage} in use
     * @throws IOException Error with accessing file or {@link java.io.FileWriter}
     * @throws EmptyDescriptionException Error when no description is provided
     * @throws ParseException Error when incorrect format for {@link java.util.Date} is used
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException, EmptyDescriptionException, ParseException;

    /**
     * Tests if command is exit command.
     *
     * @return Returns true if command is exit command
     */
    public abstract boolean isExit();
}
