import java.io.IOException;

public class Command {

    protected String action;
    protected String variable;
    private static boolean canExit = false;

    public Command(String action) {
        this.action = action;
    }

    public Command(String action, String variable) {
        this.action = action;
        this.variable = variable;
    }

    public boolean shouldExit() {
        return canExit;
    }

    public boolean exitSwitch() { return canExit = !canExit; }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        return "";
    }

}
