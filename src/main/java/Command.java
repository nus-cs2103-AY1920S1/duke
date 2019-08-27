import java.util.ArrayList;

interface Command {
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException;

    public default boolean isExit() {
        return false;
    }
}