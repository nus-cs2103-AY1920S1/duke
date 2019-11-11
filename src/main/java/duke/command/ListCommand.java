package duke.command;

import duke.TaskList;
import static duke.ui.TextUi.NEWLINE;

public class ListCommand extends Command {
    protected TaskList taskList;

    public ListCommand() {
        super.carryOn = true;
    }

    @Override
    public String execute(TaskList taskList) {
        String rtn = "Here are the " + taskList.getSize()
                + " tasks in thou list:" + NEWLINE;
        this.taskList = taskList;

        for (int i = 0; i < taskList.getSize(); i++) {
            rtn += (i + 1) + "." + taskList.get(i) + NEWLINE;
        }
        return rtn;
    }
}