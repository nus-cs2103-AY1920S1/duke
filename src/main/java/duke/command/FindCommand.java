package duke.command;

import duke.DukeException;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Searches for list items containing a keyword and returns them in a list.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String[] inputSplit, String keyword) {
        super(inputSplit);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputSplit.length == 1) {
            throw new DukeException(":( OOPS!!! Please provide a keyword to find.");
        }
        ArrayList<Task> taskArrayList = tasks.toArrayList();
        List<Task> temp =
                taskArrayList.parallelStream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        ui.printSearchList(temp);
    }
}
