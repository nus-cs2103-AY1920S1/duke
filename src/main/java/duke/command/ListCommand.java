package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int index = 0;
        System.out.println("Here are the tasks in your list: ");
        for (Task task : tasks.getList()) {
            index++;
            System.out.println(index + " " + task.toString());
        }
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
        if (!(o instanceof ListCommand)) {
            return false;
        } else {
            return true;
        }

    }
}
