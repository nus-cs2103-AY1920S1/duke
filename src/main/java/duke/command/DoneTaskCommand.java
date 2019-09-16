package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public class DoneTaskCommand extends Command {

    private String itemIndex;

    public DoneTaskCommand(String itemIndex) {
        this.itemIndex = itemIndex;
    }

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        if (index < 0 || index >= tasklist.size()) {
            throw new InvalidIndexException();
        } else {
            Task item = tasklist.get(index);
            // tick completed task
            item.setDone();
            ui.sendMessage("Nice! I've marked this task as done: ");
            ui.sendMessage("  " + item.toString());
        }
    }

}
