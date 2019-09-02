/**
 * The DeleteCommand class takes care of any commands that would
 delete tasks from the current task list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new DeleteCommand object.
     * @param commandText The portion of text that contains the details of the task.
     */
    public DeleteCommand(String commandText) {
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
    public void execute(TaskList task, Ui ui, Storage storage) throws  DukeException{
            int sizeOfList = task.getNumOfTasks();
            if (desc.matches("^\\d+")) {
                int taskNum = Integer.parseInt(desc);
                if (taskNum > sizeOfList || taskNum < 1) {
                    throw new InvalidDescriptionException("Wrong description");
                } else {
                    ui.showText(task.removeTask(taskNum));
                }
            } else {
                throw new InvalidDescriptionException("Wrong description");
            }

    }
}
