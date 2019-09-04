/**
 * ExitCommand extends Command.
 */
public class ExitCommand extends Command {
    String message;

    public ExitCommand() {
        message = " Bye. Hope to see you again soon!\n";
    }

    public ExitCommand(String str){
        message = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return Ui.frontSpace + message;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
