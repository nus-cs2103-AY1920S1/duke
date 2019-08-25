package Commands;

import DirectProcessor.TaskList;
import DirectProcessor.Ui;

public class FinishCommand extends Command {

    private int position;

    public FinishCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tl, Ui ui) {
        ui.showFinishMessage(tl.finishTask(position));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
