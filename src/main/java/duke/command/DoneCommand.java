package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        int index = Integer.valueOf(command.split(" ")[1]);
        if (index >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! That number you put in does not exit");
        }
        tasks.getList().get(index - 1).complete();
        storage.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof DoneCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        DoneCommand c = (DoneCommand) o;

        return this.command.equals(c.command);
    }
}
