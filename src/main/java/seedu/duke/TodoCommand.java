package seedu.duke;

import java.io.IOException;

public class TodoCommand extends Command {

    String command;

    public TodoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(new Todo(command));
        ui.printAddMsg();
        ui.printLatest(list);
        ui.printNumTask(list);
        storage.appendToFile(list);
    }

}
