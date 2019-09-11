import java.util.List;

public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public void execute(List<Task> lst, Ui ui) throws DukeException {
        ui.printNumberList(lst);
    }
}
