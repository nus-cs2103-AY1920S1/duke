import java.io.IOException;
import java.text.ParseException;

public class ByeCommand extends Command {
    public ByeCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui)
            throws IOException, ParseException {
        return ui.showBye();
    }
}
