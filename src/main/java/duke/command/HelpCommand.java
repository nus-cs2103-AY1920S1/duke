package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    private static final String PADDING = " (*) ";
    private static final String HELP_MESSAGE = "Here is a list of commands I can respond to: -\n"
            + PADDING + "list\n"
            + PADDING + "todo [description]\n"
            + PADDING + "deadline [description] /by [time]\n"
            + PADDING + "event [description] /at \n    [time]-[time]\n"
            + PADDING + "done [taskID/taskIDs]\n"
            + PADDING + "find [text]\n"
            + PADDING + "delete [taskID]\n"
            + PADDING + "save";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELP_MESSAGE;
    }

    @Override
    public boolean checkExit() {
        return false;
    }

}
