import java.util.List;

public interface Command {
    void execute(List<Task> tasks, Ui ui, Storage storage);

    default boolean isExit() {
        return false;
    }
}

