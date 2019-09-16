package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.util.List;

public class ByeCommand extends Command {

    private Task task;

    @Override
    public void execute(List<Task> tasks) {
        Ui.printMessages("\t" + "Bye. Hope to see you again soon!");
        try {
            Storage.getInstance().saveToDisk(tasks);
        } catch (Storage.StorageOperationException e) {
            Ui.printError(e);
        }
        System.exit(0);
    }
}

