package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

import java.io.FileNotFoundException;

/**
 * Represents a DoneCommand. Upon execution will mark the
 * task as done.
 *
 */

public class DoneCommand extends Command {

    /**
     * Initializes an DoneCommand instance with command.
     * The command should include the index of task to be marked done.
     *
     * @param command The index given by user.
     */
    public DoneCommand(String command) {
        assert !command.isEmpty();
        this.command = command;
    }

    /**
     * Mark the task as done based on the index in command. Update
     * the storage file after that.
     *
     * @param tasks The list of task currently recorded.
     * @param ui An UI object.
     * @param storage Storage of the current list of tasks.
     * @throws DukeException If index is negative or exceeds the size of TaskList.
     * @throws FileNotFoundException If file cannot be found.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException, FileNotFoundException {
        int index = Integer.valueOf(command);
        if (index > tasks.size() || index < 1) {
            throw new DukeException("☹ OOPS!!! That number you put in does not exit");
        }
        tasks.get(index - 1).toggleState();
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done: \n  " + tasks.get(index - 1).toString() + "\n");
        storage.updateFile(tasks);
        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Checks if this is the exit command.
     *
     * @return false as it is an DoneCommand.
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
        if (!(o instanceof DoneCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        DoneCommand c = (DoneCommand) o;

        return this.command.equals(c.command);
    }
}
