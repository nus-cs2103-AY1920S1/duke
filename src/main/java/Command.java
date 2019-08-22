import java.util.List;

interface Command {
    List<String> run(String[] words);

    default boolean isExit() {
        return false;
    }
}
