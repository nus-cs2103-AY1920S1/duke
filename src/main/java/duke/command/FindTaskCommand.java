package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.task.Task;

import java.util.List;


public class FindTaskCommand extends Command {
    public FindTaskCommand(String commandInformation) {
        super(commandInformation);
    }

    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage) {
        String searchTerm = this.commandInformation;
        List<Task> searchResultsList = tasks.findMatchingTasks(searchTerm);
        return messageHandler.searchResults(searchResultsList);
    }

}
