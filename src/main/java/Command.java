import java.io.IOException;

public abstract class Command {

    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(Sheet sh, Ui ui, Storage stor) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
