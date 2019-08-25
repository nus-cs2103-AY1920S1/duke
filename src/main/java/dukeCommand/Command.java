package dukeCommand;

import dukeHelper.Storage;
import dukeHelper.Ui;
import dukeTask.TaskList;

public abstract class Command {
    protected String filePath;
    protected String[] inputSplit;

    public Command(String filePath, String[] inputSplit) {
        this.filePath = filePath;
        this.inputSplit = inputSplit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
