/**
 * Command corresponding to user input "done".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class DoneCommand extends Command {
    protected String num;

    /**
     * Constructs a DoneCommand object.
     * @param num indicates the task to be marked as completed
     */
    public DoneCommand(String num) {
        this.num = num;
    }

    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param ui displays the output from execution
     * @param storage stores the added task to the specified file
     * @throws DukeException if task requirements is not met
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int numChange = Integer.parseInt(this.num);
        if (numChange > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Task " + numChange + " does not exist.");
        } else {
            Task done = tasks.get(numChange - 1);
            done.markAsDone();
            ui.showLine("Nice! I've marked this task as done:" + "\n" + done.toString());
            storage.save(tasks.getTaskList());
        }
    }

    /**
     * Determines whether the program stops running.
     * @return boolean value false so the program continues to run
     */
    public boolean isExit() {
        return false;
    }
}

