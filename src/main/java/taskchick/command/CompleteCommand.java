package taskchick.command;

import java.io.IOException;

import taskchick.exception.TaskChickException;
import taskchick.tasklist.TaskList;
import taskchick.storage.Storage;

/**
 * Command to mark a task as completed.
 */
public class CompleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a CompleteCommand object with the specified task number to be marked as completed assigned.
     *
     * @param taskNumber Index of the task to be marked as completed.
     */
    public CompleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Parses the command given to Task Chick and creates a CompleteCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return CompleteCommand object to be created.
     * @throws TaskChickException If no index is given.
     */
    public static CompleteCommand process(String[] fullCommand) throws TaskChickException {
        try {
            return new CompleteCommand(Integer.parseInt(fullCommand[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskChickException("OOPS!!! Please specify a task number to be marked as completed.");
        } catch (NumberFormatException e) {
            throw new TaskChickException("OOPS!!! Please specify the index of the task to be marked as completed"
                    + " as an integer.");
        }
    }

    /**
     * Executes the completion of the task.
     *
     * @param tasks Task list where the task will be marked as completed.
     * @param storage Storage to be updated with the task completed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskChickException {
        try {
            UndoCommand.saveVersion(storage.getSavedListString(tasks));
            StringBuilder sb = new StringBuilder(tasks.completeTask(taskNumber));
            storage.store(tasks);
            return sb.toString();
        } catch (IndexOutOfBoundsException e) {
            UndoCommand.removeRecentSavedVersion();
            throw new TaskChickException("OOPS!!! Your specified task number is out of range.");
        } catch (IOException e) {
            throw new TaskChickException("OOPS!!! " + e.getMessage());
        }
    }
}
