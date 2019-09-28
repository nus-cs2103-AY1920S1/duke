package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class SearchCommand extends Command {
    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList relevantTaskList = tasks.search(keyword);
        return relevantTaskList.toString();
    }
}
