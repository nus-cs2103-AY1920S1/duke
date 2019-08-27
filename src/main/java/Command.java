import java.util.ArrayList;

interface Command {
    public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException;

    public default boolean isExit() {
        return false;
    }
}