package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import duke.format.*;

public class AddDeadlineCommand extends Command {

    String task;
    String deadline;

    public AddDeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else {
            DateTime dateTime = new DateTime(deadline);
            tasklist.add(new Deadline(task, dateTime.toReformat()));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }
}
