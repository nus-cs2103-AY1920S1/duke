package duke.command;

/**
 * Data structure to wrap the command to mark a task as done in the task list
 */
public class CompleteTaskCommand extends Command {

    /**
     * Constructs the command to mark a task as done
     * @param taskNumber The number corresponding position of the task in the task list to be marked as done
     */
    public CompleteTaskCommand(String taskNumber) {
        super(Type.COMPLETE, taskNumber);
    }
}