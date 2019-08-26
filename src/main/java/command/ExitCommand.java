package command;
import main.Storage;
import main.Ui;
import main.TaskList;

public class ExitCommand implements Command {

    public ExitCommand() {

    }
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return ;
    }

    @Override
    public String toString() {
        return "Command: exit";
    }
}
