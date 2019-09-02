import java.io.IOException;
import java.util.List;

interface Command {
    List<String> run(String[] words) throws DukeException, IOException;

    default boolean isExit() {
        return false;
    }
}
