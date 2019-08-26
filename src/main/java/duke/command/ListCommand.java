package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("Here are the tasks in your list:\n");
        myBuilder.append(tasks.toString());
        ui.printMessage(myBuilder.toString());
    }
}
