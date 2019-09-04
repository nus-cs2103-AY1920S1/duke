package duke.command;

import java.io.IOException;
import java.util.List;

import duke.DukeException;

public interface Command {
    /**
     * Returns the message to show the user after the command is run.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     */
    List<String> run(String[] words) throws DukeException, IOException;

    /**
     * Returns whether the program should exit after this command.
     *
     * @return false by default, most commands should not exit the program.
     */
    default boolean isExit() {
        return false;
    }
}
