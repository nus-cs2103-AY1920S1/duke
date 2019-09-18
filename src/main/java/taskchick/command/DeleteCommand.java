package taskchick.command;

import java.io.IOException;
import taskchick.exception.TaskChickException;
import taskchick.tasklist.TaskList;
import taskchick.storage.Storage;

/**
 * Command to remove tasks from the task list.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DeleteCommand object with the specified task number to be deleted assigned.
     *
     * @param taskNumber Index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Parses the command given to Duke and creates a DeleteCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return DeleteCommand object to be created.
     * @throws TaskChickException If no index is given.
     */
    public static DeleteCommand process(String[] fullCommand) throws TaskChickException {
        try {
            return new DeleteCommand(Integer.parseInt(fullCommand[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskChickException("OOPS!!! Please specify the index of the task to be deleted.");
        } catch (NumberFormatException e) {
            throw new TaskChickException("OOPS!!! Please specify the index of the task to be deleted as an "
                    + "integer.");
        }
    }

    /**
     * Executes the remove command.
     *
     * @param tasks Task list where the task should be deleted from.
     * @param storage Storage to be updated with the task removed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskChickException {
        try {
            UndoCommand.saveVersion(storage.getSavedListString(tasks));
            StringBuilder sb = new StringBuilder();
            sb.append(tasks.deleteTask(taskNumber));
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
