package command;

import run.Storage;
import run.TaskList;
import run.Ui;

public class FindCommand extends Command {
    protected String rawString;
    protected String searchString;

    public FindCommand(String rawString) {
        this.rawString = rawString;
        this.searchString = rawString.replace("find ", "");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(this.searchString);
    }

    /**
     * Checks if this command is an exit ("bye") command
     * @return false boolean since command is not exit ("bye") command
     */
    public boolean isExit() {
        return false;
    }
}