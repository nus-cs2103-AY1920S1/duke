/**
 * Command corresponding to user input "done".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class DoneCommand extends Command {
    private int intNum;

    /**
     * Constructs a DoneCommand object.
     * @param stringNum indicates the task to be marked as completed
     * @throws DukeException if the string is not a number
     */
    public DoneCommand(String stringNum) throws DukeException {
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
        if (intNum > tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! Task " + intNum + " does not exist.");
        }

        Task done = tasks.get(intNum - 1);
        done.markAsDone();
        String printable = ("Nice! I've marked this task as done:" + "\n" + done.toString());

        storage.save(tasks.getTaskList());
        return printable;
    }
}

