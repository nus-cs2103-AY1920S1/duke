package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.trackables.Task;

import java.util.List;

public class ByeCommand extends Command {

    private Task task;

    @Override
    public void execute(List<Task> tasks) {
        echo("\t" + "Bye. Hope to see you again soon!");
        try {
            Storage.getInstance().saveToDisk(tasks);
        } catch (Storage.StorageOperationException e) {
            echo(e.getMessage());
        }
        System.exit(0);
    }
}

