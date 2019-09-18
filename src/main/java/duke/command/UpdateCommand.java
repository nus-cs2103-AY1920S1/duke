package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

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
     * Parses the command given to Duke and creates a UpdateCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return UpdateCommand object to be created.
     * @throws DukeException If input is not in the format update [task number] [new description].
     */
    public static UpdateCommand process(String[] fullCommand) throws DukeException {
        try {
            String[] currArray = fullCommand[1].split("\\s+", 2);
            return new UpdateCommand(Integer.parseInt(currArray[0]), currArray[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Please enter in the format update [task number] [new "
                    + "description]");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please specify the index of the task as an integer.");
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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            UndoCommand.saveVersion(storage.getSavedListString(tasks));
            StringBuilder sb = new StringBuilder();
            sb.append(tasks.updateDescription(taskNumber, newDescription));
            storage.store(tasks);
            return sb.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Your specified task number is out of range.");
        } catch (IOException e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }
}
