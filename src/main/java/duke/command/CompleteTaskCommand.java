package duke.command;

/**
 * A Command which is used to instruct Duke to mark a Task in the TaskList as completed.
 */
public class CompleteTaskCommand extends Command {

    /**
     * Constructs a Command to instruct Duke to mark a Task in the TaskList as completed.
     *
     * @param taskNumber The number corresponding to the Task in the TaskList to be marked as completed
     */
    CompleteTaskCommand(String taskNumber) {
        super(Type.COMMAND_COMPLETE_TASK, taskNumber);
        assert taskNumber != null;
    }
}