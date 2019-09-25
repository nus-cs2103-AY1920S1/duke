import java.io.IOException;

public class EventCommand extends Command {

    /**
     * Constructor for Event Command.
     *
     * @param action Description of the task.
     * @param variable Date to finish the tasks.
     */
    public EventCommand(String action, String variable) {
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
        TaskList tasks = new TaskList();
        Task assignmentToDo = new Event(action, variable);
        tasks.addToTaskList(assignmentToDo);
        errands.addToCompleteList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        String eventOutput = ui.printGI() + "\n";
        eventOutput += "  " + assignmentToDo.toString() + "\n";
        eventOutput += Ui.printNumOfTasks();
        return eventOutput;
    }
}