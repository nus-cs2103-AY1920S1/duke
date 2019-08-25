package Commands;

import DirectProcessor.TaskList;
import DirectProcessor.Ui;

public class FakeCommand extends Command {

    public FakeCommand() {}

    @Override
    public void execute(TaskList tl, Ui ui) {
        ui.showError("This is an invalid command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
