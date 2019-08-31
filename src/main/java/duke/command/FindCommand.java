package duke.command;

import duke.storage.DukeStorage;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;

public class FindCommand extends Command {
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    public void execute(MyList taskList, DukeUserInterface ui, DukeStorage storage) {
        MyList resultList = taskList.findTasks(word);
        ui.printFindList(resultList);
    }
}
