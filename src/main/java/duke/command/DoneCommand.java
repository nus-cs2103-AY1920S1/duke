package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

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
     * @param command The full message received from user.
     */
    public DoneCommand(String command) {
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
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        int index = Integer.valueOf(command.split(" ")[1]);
        if (index >= tasks.size() || index < 1) {
            throw new DukeException("\u2639 OOPS!!! That number you put in does not exit");
        }
        tasks.get(index - 1).toggleState();
        System.out.println("Nice! I've marked this task as done: \n  " + tasks.get(index - 1).toString());
        storage.updateFile(tasks);
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
