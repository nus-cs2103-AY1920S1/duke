package seedu.duke.model;

import seedu.duke.core.Ui;
import seedu.duke.model.dto.Task;

import java.util.List;

public class CmdList extends Cmd {
    private Ui ui;
    private List<Task> taskList;

    public CmdList(List<Task> taskList, String output)  {
        super();
        this.taskList = taskList;
        this.ui = new Ui();
        this.setMsg(ui.displayList(output, taskList));
    }
}
