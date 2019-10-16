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
        assert !command.isEmpty();
        this.command = command;
    }

    /**
     * Loop through the task list and search for similar tasks.
     * Print out tasks that matches search term.
     * @param tasks TaskList
     * @param ui UI
     * @param storage Storage containing file.
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks.getList()) {
            if (task.getContent().contains(command)) {
                result.add(task);
            }
        }
        sb.append("Here are the matching tasks in your list:\n");
        int index = 0;
        for (Task task : result) {
            index++;
            sb.append(index + " " + task.toString() + "\n");
        }
        String response = sb.toString();
        System.out.println(response);
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Get the command string
     */
    public String getCommand() {
        return this.command;
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
