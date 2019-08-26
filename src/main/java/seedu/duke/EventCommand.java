package seedu.duke;

import java.io.IOException;
import java.util.Date;

public class EventCommand extends Command {

    String command;
    Date dateTime;

    public EventCommand(String command, Date dateTime) {
        this.command = command;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(new Event(command, dateTime));
        ui.printAddMsg();
        ui.printLatest(list);
        ui.printNumTask(list);
        storage.appendToFile(list);
    }
}
