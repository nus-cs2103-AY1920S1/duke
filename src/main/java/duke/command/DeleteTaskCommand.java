package duke.command;

/**
 * A Command which is used to instruct Duke to remove a Task from the TaskList.
 */
public class DeleteTaskCommand extends Command {

    /**
     * Constructs the Command to instruct Duke to remove a task from the TaskList.
     *
     * @param taskNumber The number corresponding to the Task in the TaskList to be removed
     */
    DeleteTaskCommand(String taskNumber) {
        super(Type.COMMAND_DELETE_TASK, taskNumber);
        assert taskNumber != null;
    }
}