package Commands;

import DirectProcessor.Storage;
import DirectProcessor.TaskList;
import DirectProcessor.Ui;

public class DeleteCommand extends Command{

    private int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tl, Ui ui) {
        ui.showDeleteMessage(tl.deleteTask(position));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
