package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.util.List;


public class FindTaskCommand extends Command {
    public FindTaskCommand(String commandInformation) {
        super(commandInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        String searchTerm = this.commandInformation;
        List<Task> searchResultsList = tasks.findMatchingTasks(searchTerm);
        ui.printSearchResults(searchResultsList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
