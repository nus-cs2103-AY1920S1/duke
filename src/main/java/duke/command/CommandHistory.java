package duke.command;

import duke.task.TaskList;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

public class CommandHistory extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) {
        return ui.getHistoryMessage(history.toString());
    }
}
