import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        ui.printNumberList(taskList);
    }
}
