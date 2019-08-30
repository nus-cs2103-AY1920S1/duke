package duke.command;

import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.command = "bye";
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ExitCommand)) {
            return false;
        } else {
            return true;
        }
    }
}
