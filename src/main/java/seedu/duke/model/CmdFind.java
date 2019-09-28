package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.core.Ui;
import seedu.duke.model.dto.Task;

import java.util.List;

public class CmdFind extends Cmd {
    public CmdFind(Ui ui, String output, Storage storage, List<Task> list, String description) {
        this.setMsg(ui.displayList(output, storage.searchTask(list, description.split(" "))));
    }
}
