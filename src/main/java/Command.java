import java.util.ArrayList;

public abstract class Command {

    public static boolean isExit = false;

    public static boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
