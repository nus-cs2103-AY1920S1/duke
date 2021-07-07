package commands;

import java.io.IOException;
import core.Storage;
import core.TaskList;
import exceptions.DukeException;

public abstract class Command {

    /**
     * Executes the command inputted by the user into duke and
     * returns the string representation of Core.Duke's reply to the user
     * inside a stringbuilder object.
     * @param input The user's command.
     * @param taskList The taskList object handling all tasks.
     * @param storage The Core.Storage object needed to load and write back to the tasks file.
     * @return The string representation of Core.Duke's response to the user's command, inside a stringbuilder object.
     * @throws DukeException If any exceptional situation was encountered while executing the user's command.
     * @throws IOException If any exceptional situation was encountered while writing or reading from storage.
     */
    public abstract StringBuilder execute(
            String input, TaskList taskList, Storage storage) throws DukeException, IOException;

}