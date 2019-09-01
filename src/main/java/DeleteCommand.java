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

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //no input number
            if (ui.getInputArr().length == 1) {
                throw new NumberFormatException();
            }
            int num = Integer.parseInt(ui.getInputArr()[1]);
            //invalid num, will index out of bounds
            if (num > tasks.getSize()) {
                throw new NumberFormatException();
            } else {
                Duke.print("Noted. I've removed this task:\n" +
                        "     " + tasks.getTask(num - 1) + "\n" +
                        "     Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
                tasks.deleteTask(num - 1);
            }
        } catch (NumberFormatException e) {
            Duke.print("☹ OOPS!!! Please input a valid number.");
        }
    }

}
