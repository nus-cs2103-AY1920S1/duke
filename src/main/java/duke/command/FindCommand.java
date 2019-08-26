package duke.command;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String findText;

    public FindCommand(String findText) {
        super(false);
        this.findText = findText;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ArrayList<Task> matchingWordsArr = taskList.getMatchingWordsList(findText);
        ui.showFoundTasks(matchingWordsArr);
    }
}
