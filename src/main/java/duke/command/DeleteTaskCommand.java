package duke.command;

/**
 * Data structure to wrap a command to delete a task in the task list
 */
public class DeleteTaskCommand extends Command {

    /**
     * Constructs the command to mark a task as done
     *
     * @param taskNumber The number corresponding position of the task in the task list to be marked deleted
     */
    public DeleteTaskCommand(String taskNumber) {
        super(Type.DELETE, taskNumber);
    }
}