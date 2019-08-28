import java.util.ArrayList;

public interface Command {
    public void execute(Ui ui, Storage storage, TaskList taskList);
    public default boolean isExit() {
        return false;
    }
}
