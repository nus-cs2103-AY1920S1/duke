package Commands;

import DirectProcessor.Storage;
import DirectProcessor.TaskList;
import DirectProcessor.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(TaskList tl, Ui ui) {
        try{
            tl.rewrite();
        } catch (IOException e) {
            ui.showError("Unable to save current tasks.");
        }
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
