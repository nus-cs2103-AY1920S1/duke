/**
 * Delete command that inherits from Command, deletes a task from TaskList.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param input input from user.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Deletes a Task from the current TaskList.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputArr = input.split(" ");
        //no input number
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! Please input a valid number.");
        }
        int num = Integer.parseInt(inputArr[1]);
        //invalid num, will index out of bounds
        if (num > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! Please input a valid number.");
        } else {
            tasks.deleteTask(num - 1);
            ui.setResponse("Noted. I've removed this task:\n"
                    +
                    "     " + tasks.getTask(num - 1) + "\n"
                    +
                    "     Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
            storage.save(tasks);

        }
    }
}

