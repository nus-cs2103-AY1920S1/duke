import java.text.Normalizer;

public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printListCommand(taskList);
    }
}
