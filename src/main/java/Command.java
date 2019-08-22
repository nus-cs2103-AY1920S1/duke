import java.util.List;

interface Command {
    List<String> run(String[] words) throws DukeException;

    default boolean isExit() {
        return false;
    }
}
