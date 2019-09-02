package duke.command;

import java.io.IOException;
import java.util.List;

import duke.DukeException;

public interface Command {
    List<String> run(String[] words) throws DukeException, IOException;

    default boolean isExit() {
        return false;
    }
}
