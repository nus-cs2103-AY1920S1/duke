package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import duke.format.*;

public class AddEventCommand extends Command {

    private String task;
    private String time;

    public AddEventCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else {
            DateTime dateTime = new DateTime(time);
            tasklist.add(new Event(task, dateTime.toReformat()));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }
}
