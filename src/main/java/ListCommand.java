import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showListMessage(taskList);
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
