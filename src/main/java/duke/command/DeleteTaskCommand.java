package duke.command;

/**
 * A Command which is used to tell Duke remove a Task in the TaskList.
 */
public class DeleteTaskCommand extends Command {

    /**
     * Constructs the Command to tell Duke to remove a task form the TaskList
     *
     * @param taskNumber The number corresponding position of the Task in the TaskList to be marked as done
     */
    DeleteTaskCommand(String taskNumber) {
        super(Type.COMMAND_DELETE_TASK, taskNumber);
    }
}