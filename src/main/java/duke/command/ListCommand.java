package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("\t Here are the tasks in your list: ");
        for (int i = 0; i < tasks.getListSize(); i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks.getTask(i));
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}
