package taskchick.command;

import taskchick.exception.TaskChickException;
import taskchick.storage.Storage;
import taskchick.tasklist.TaskList;

import java.io.IOException;

/**
 * Command to update the description of a task.
 */
public class UpdateCommand extends Command {

    private int taskNumber;
    private String newDescription;

    /**
     * Creates a UpdateCommand object with the task number and new description to be updated assigned.
     *
     * @param taskNumber Index of the task to be updated.
     * @param newDescription Updated description of the task.
     */
    public UpdateCommand(int taskNumber, String newDescription) {
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
    }

    /**
     * Parses the command given to Task Chick and creates a UpdateCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return UpdateCommand object to be created.
     * @throws TaskChickException If input is not in the format update [task number] [new description].
     */
    public static UpdateCommand process(String[] fullCommand) throws TaskChickException {
        try {
            String[] currArray = fullCommand[1].split("\\s+", 2);
            return new UpdateCommand(Integer.parseInt(currArray[0]), currArray[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskChickException("OOPS!!! Please enter in the format update [task number] [new "
                    + "description]");
        } catch (NumberFormatException e) {
            throw new TaskChickException("OOPS!!! Please specify the index of the task as an integer.");
        }
    }

    /**
     * Executes the UpdateCommand.
     *
     * @param tasks Task list to refer to.
     * @param storage Storage where the list is updated with the new description.
     * @return Response to user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskChickException {
        try {
            UndoCommand.saveVersion(storage.getSavedListString(tasks));
            StringBuilder sb = new StringBuilder();
            sb.append(tasks.updateDescription(taskNumber, newDescription));
            storage.store(tasks);
            return sb.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskChickException("OOPS!!! Your specified task number is out of range.");
        } catch (IOException e) {
            throw new TaskChickException("OOPS!!! " + e.getMessage());
        }
    }
}
