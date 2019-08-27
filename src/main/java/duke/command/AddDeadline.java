package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents a command to add a deadline object to TaskList.
 */
public class AddDeadline extends Command {
    /** Description and deadline of task entered by user.*/
    protected String userIn;

    /**
     * Initializes an AddDeadline Object with the user input.
     * @param userIn description and deadline of task entered by user.
     */
    public AddDeadline(String userIn) {
        this.userIn = userIn;
    }

    /**
     * Adds a Deadline task object into the list of tasks and prints out a message to
     * inform the user of such.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @throws DukeException if user input is invalid.
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] splitUserIn = userIn.split(" /by ");
            Task newDl = new Deadline(splitUserIn[0], splitUserIn[1]);
            tasks.addTask(newDl);
            System.out.println("Got it. I've added this task: \n" + newDl + "\nNow you have "
                    + tasks.getSize() + " task(s) in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid description");
        }

    }
}
