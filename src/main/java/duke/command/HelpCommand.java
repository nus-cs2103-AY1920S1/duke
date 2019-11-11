package duke.command;

import duke.TaskList;
import static duke.ui.TextUi.NEWLINE;

public class HelpCommand extends Command {

    public HelpCommand() {
        super.carryOn = true;
    }

    @Override
    public String execute(TaskList taskList) {
        String rtn = "Here are the commands available:" + NEWLINE
                + "bye" + NEWLINE
                + "delete [index]" + NEWLINE
                + "done [index]" + NEWLINE
                + "find [keyword]" + NEWLINE
                + "list" + NEWLINE
                + "undo" + NEWLINE
                + "view [index]" + NEWLINE
                + "todo [task]" + NEWLINE
                + "event [task] /at [date time]" + NEWLINE
                + "deadline [task] /by [date time]";
        return rtn;
    }

}
