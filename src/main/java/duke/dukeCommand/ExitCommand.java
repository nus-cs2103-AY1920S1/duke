package duke.dukeCommand;

import duke.dukeHelper.Storage;
import duke.dukeHelper.Ui;
import duke.dukeTask.TaskList;

public class ExitCommand extends Command {

    public ExitCommand(String filePath, String inputSplit[]) {
        super(filePath, inputSplit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
