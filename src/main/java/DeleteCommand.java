/**
 * Command corresponding to user input "delete".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class DeleteCommand extends Command {
    private int intNum;

    /**
     * Constructs a DeleteCommand object.
     * @param stringNum indicates the task to be deleted
     */
    public DeleteCommand(String stringNum) throws DukeException {
        try {
            this.intNum = Integer.parseInt(stringNum);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param storage stores the added task to the specified file
     * @throws DukeException if task requirements is not met
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (this.intNum > tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! Task " + intNum + " does not exist.");
        }

        Task toRemove = tasks.get(intNum - 1);
        tasks.remove(intNum - 1);
        String printable = ("Noted. I've removed this task:" + "\n" + toRemove.toString() +
                    "\n" + "Now you have " + tasks.size() + " tasks in the list.");

        storage.save(tasks.getTaskList());
        return printable;
    }
}
