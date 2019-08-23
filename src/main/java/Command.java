import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class Command {

    protected boolean isExit;
    protected String fullCommand;

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

}
