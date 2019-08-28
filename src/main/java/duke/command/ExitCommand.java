package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ExitCommand extends Command {

    public ExitCommand(String taskInformation) {
        super(taskInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
