package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Represents a ListCommand. Upon execution will show user all the tasks in the list.
 *
 */
public class ListCommand extends Command {

    /**
     * Iterates through each item in the list and show it to user.
     *
     * @param tasks Task list that holds the current tasks
     * @param ui The ui that will handle output
     * @param storage Connects to the storage file
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (Task task : tasks.getList()) {
            index++;
            sb.append(index + ". " + task.toString() + "\n");
        }
        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Checks if this is the exit command.
     *
     * @return false as it is a ListCommand.
     */
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

    /**
     * Compare another object with this object to see if the other object is ListCommand.
     *
     * @param o The other object that is going to be compared to this.
     * @return true or false based on the execution of the method.
     */
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
