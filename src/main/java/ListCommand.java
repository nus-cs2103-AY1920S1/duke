import java.io.IOException;
import java.text.ParseException;

public class ListCommand extends Command {
    public ListCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException, ParseException {
        return taskList.showList();
    }
}
