package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String argument;

    public FindCommand(String argument) {
        this.argument = argument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("Here are the matching tasks in your list:\n");
        myBuilder.append(tasks.findTask(argument).toString());
        ui.printMessage(myBuilder.toString());
    }
}
