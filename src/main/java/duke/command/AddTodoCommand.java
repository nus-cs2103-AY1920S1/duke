package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public class AddTodoCommand extends Command{

    String item;

    public AddTodoCommand(String item) {
        this.item = item;
    }

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        if (tasklist.size() >= 100) {
            ui.sendMessage("You can add no more than 100 tasks!");
        } else {
            tasklist.add(new Todo(item));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }

}
