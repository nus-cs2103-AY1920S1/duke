package duke.command;

/**
 * A Command which is used to tell Duke mark a Task in the TaskList as completed.
 */
public class CompleteTaskCommand extends Command {

    /**
     * Constructs the Command to mark a Task as completed
     *
     * @param taskNumber The number corresponding position of the Task in the TaskList to be marked as done
     */
    CompleteTaskCommand(String taskNumber) {
        super(Type.COMMAND_COMPLETE_TASK, taskNumber);
    }
}