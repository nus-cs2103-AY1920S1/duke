package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;
import duke.command.Command;
/**
 * Represents a find command.
 */
public class FindCommand extends Command {

    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Loop through the task list and search for similar tasks.
     * Print out tasks that matches search term.
     * @param tasks TaskList
     * @param ui UI
     * @param storage Storage containing file.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks.getList()) {
            if (task.getContent().contains(command)) {
                result.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        int index = 0;
        for (Task task : result) {
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
        if (!(o instanceof DoneCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        FindCommand c = (FindCommand) o;

        return this.command.equals(c.command);
    }
}
