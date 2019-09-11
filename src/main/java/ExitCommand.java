import java.util.List;


public class ExitCommand extends Command {

    public ExitCommand(){
        this.commandType = CommandType.EXIT;
    }

    @Override
    public void execute(List<Task> lst, Ui ui) throws DukeException {
        ui.printByeMessage();
    }
}

