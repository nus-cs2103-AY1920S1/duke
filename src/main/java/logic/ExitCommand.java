package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

public class ExitCommand implements Command {

    /**
     * Creates an instance of ExitCommand
     */
    public ExitCommand() {

    }

    /**
     * Parses the arguments of the Command and executes it
     *
     * @param tasks   the TaskList of Tasks
     * @param ui      The User Interface
     * @param storage Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "Bye. Hope to see you again soon!";
        return content;
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
