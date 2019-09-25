import java.io.IOException;

public class DeadlineCommand extends Command {

    /**
     * Constructor for Deadline Command.
     *
     * @param action Description of the task.
     * @param variable Date to finish the tasks.
     */
    public DeadlineCommand(String action, String variable) {
        super(action, variable);
    }

    /**
     * Executes the deadline command and prints out statements to
     * tell the user that the deadline tasks has been added to
     * the list of tasks.
     *
     * @param tasks Adds the deadline to the list of tasks.
     * @param ui Prints out statements to indicate to user what
     *           has happened.
     * @param storage Stores the tasks inside another file so that
     *                the task will be available the next time the
     *                bot starts up.
     * @return Returns String to print out to the user.
     * @throws IOException If the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     */
    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        Task assignmentToDo = new Deadline(action, variable);
        TaskList tasks = new TaskList();
        tasks.addToTaskList(assignmentToDo);
        errands.addToCompleteList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        String deadlineOutput = ui.printGI() + "\n";
        deadlineOutput += "  " + assignmentToDo.toString() + "\n";
        deadlineOutput += Ui.printNumOfTasks();
        return deadlineOutput;
    }
}