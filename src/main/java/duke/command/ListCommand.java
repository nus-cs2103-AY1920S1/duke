package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * This class controls the list command.
 */
public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        return ui.list(list);
    }
}