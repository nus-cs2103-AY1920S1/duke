package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Here is a list of commands I can respond to: -\n"
            + "LIST\n  * List out all the current tasks\n"
            + "TODO [description]\n  * Stores a task to be done\n"
            + "DEADLINE [description] /by [time]\n  * Stores a task to be completed\n     by the specified deadline.\n"
            + "EVENT [description] /at [time]-[time]\n  * Stores an event to be done\n"
            + "DONE [taskID/taskIDs]\n  * Marks the tasks as done\n"
            + "FIND [text]\n  * Lists the tasks whose\n     descriptions match the input text\n"
            + "DELETE [taskID]\n  * Deletes the task indexed taskID\n";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELP_MESSAGE;
    }

    @Override
    public boolean checkExit() {
        return false;
    }

}
