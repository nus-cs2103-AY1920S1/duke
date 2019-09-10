package seedu.duke.model;

import seedu.duke.core.Ui;
import seedu.duke.model.dto.Task;

import java.util.List;

public class CmdList extends Cmd {
    private Ui ui;
    private List<Task> taskList;

    /**
     * Subclass of Cmd, which is handles list action command.
     * @param taskList task list (Array List).
     * @param output string which will be appended with the action result.
     */
    public CmdList(List<Task> taskList, String output) {
        super();
        this.taskList = taskList;
        this.ui = new Ui();
        this.setMsg(ui.displayList(output, taskList));
    }
}
