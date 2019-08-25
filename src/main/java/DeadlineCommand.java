import java.io.IOException;
import java.util.Date;

public class DeadlineCommand extends Command {

    String command;
    Date dateTime;

    public DeadlineCommand(String command, Date dateTime) {
        this.command = command;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(new Deadline(command, dateTime));
        ui.printAddMsg();
        ui.printLatest(list);
        ui.printNumTask(list);
        storage.appendToFile(list);
    }
}
