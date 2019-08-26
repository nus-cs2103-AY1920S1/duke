/**
 * Represents the command of marking a Task object as done from a Duke object's TaskList object.
 */
public class DoneCommand extends Command {
    protected int taskNumber;

    /**
     * Creates a DoneCommand object.
     *
     * @param taskNumber Index of the Task object to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the marking of the Task object from a Duke object's TaskList object as done.
     * The given index of task to be marked as done is first checked to be valid or not.
     * After being marked, the marked task as done message is displayed.
     *
     * @param tasks TaskList object that contains task to be marked as done.
     * @param ui Duke object's Ui object to display task marked as done  message.
     * @param storage Duke object's Storage object to access file for loading/saving tasks.
     * @throws InvalidCommandDukeException Thrown when an invalid task number index is given so no Task
     * object can be retrieved from the TaskList object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandDukeException {
        if (taskNumber >= 0 && taskNumber < tasks.taskListSize()) {
            tasks.markAsDone(taskNumber);
            ui.showMarkTaskAsDoneMessage(tasks.getTask(taskNumber));
        } else {
            throw new InvalidCommandDukeException("☹ OOF!! There is no task labelled that number!!");
        }

    }

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return DoneCommand is not the ExitCommand so it returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
