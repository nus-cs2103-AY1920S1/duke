import java.io.IOException;

public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Instantiates DeleteCommand object.
     * @param deleteIndex the task number inserted by the user to be deleted
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Returns whether the command is an ExitCommand.
     * @return whether the command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes given task to the saved task-list and prints
     * a confirmation if the addition of the new task is successful.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     * @return message response to user
     * @throws DukeException if the deletion index does not exist
     */
    @Override
    public String executeAndReturnMessage(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (deleteIndex > tasks.getNumOfTasks() || deleteIndex <= 0) {
            throw new DukeException("The specified task number does not exist");
        }
        Task chosenTask = tasks.getTask(deleteIndex - 1);
        tasks.removeTask(deleteIndex - 1);
        try {
            storage.deleteFromFile(deleteIndex);
            return "Noted. I've removed this task:\n  " + chosenTask.toString() + "\n"
                    + "Now you have " + tasks.tasks.size()
                    + (tasks.tasks.size() == 1 ? " task" : " tasks") + " in the list.\n";
        } catch (IOException ex) {
            return "Error when deleting task from saved file\n";
        }
    }
}
