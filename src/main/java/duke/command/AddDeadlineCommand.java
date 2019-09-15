package duke.command;

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

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        if (tasklist.size() >= 100) {
            ui.sendMessage("You can add no more than 100 tasks!");
        } else {
            DateTime dateTime = new DateTime(deadline);
            tasklist.add(new Deadline(task, dateTime.toString()));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }
}
