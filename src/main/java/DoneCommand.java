/**
 * The DoneCommand class that marks the stated task on the current task list
 as done.
 */
public class DoneCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new DoneCommand object.
     * @param commandText The portion of text that contains the details of the task.
     */
    public DoneCommand(String commandText) {
        super();
        desc = commandText;
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     * @throws DukeException Possibility of throwing a DukeException due to
     *      an exception occuring in the running of the application.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
            int sizeOfList = task.getNumOfTasks();
            String number = desc.substring(4).trim();
            if (number.matches("^\\d+")) {
                int taskNum = Integer.parseInt(number);
                if (taskNum > sizeOfList || taskNum < 1) {
                    throw new InvalidDescriptionException("Wrong description");
                } else {
                    ui.showText(task.tickTask(taskNum));
                }
            } else {
                throw new InvalidDescriptionException("Wrong description");
            }
    }
}
