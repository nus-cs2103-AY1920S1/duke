package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Represents a DeleteCommand. Upon execution will delete the
 * task accordingly from a given TaskList.
 *
 */

public class DeleteCommand extends Command {

    /**
     * Initializes an DeleteCommand instance with command.
     * The command should include the index of task to be deleted.
     *
     * @param command The full message received from user.
     */
    public DeleteCommand(String command) {
        assert !command.isEmpty();
        this.command = command;
    }

    /**
     * Deletes the task base on the index in command. Update
     * the storage file after that.
     *
     * @param tasks The list of task currently recorded.
     * @param ui An UI object.
     * @param storage Storage of the current list of tasks.
     * @throws DukeException If index is negative or exceeds the size of TaskList.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        int index = Integer.parseInt(command);
        if (index > tasks.size() || index < 1) {
            throw new DukeException("☹ OOPS!!! That number you put in does not exit");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task: \n");
        sb.append("  " + task.toString() + "\n");
        if (tasks.size() == 1) {
            sb.append("Now you have 1 task in the list\n");
        } else {
            sb.append(String.format("Now you have %d tasks in the list\n", tasks.size()));
        }
        storage.updateFile(tasks);
        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Checks if this is the exit command.
     *
     * @return false as it is an DeleteCommand.
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
     * Compare another object with this object to see if they have the same
     * information.
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
        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        DeleteCommand c = (DeleteCommand) o;

        return this.command.equals(c.command);
    }
}
