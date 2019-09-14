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
     * @param commandArray Containing task number in the first entry and new description in the second entry.
     * @throws NumberFormatException If index of task to be updated is not a number.
     */
    public UpdateCommand(String[] commandArray) throws NumberFormatException {
        try {
            this.taskNumber = Integer.parseInt(commandArray[0]);
            this.newDescription = commandArray[1];
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please specify the index of the task to be updated.");
        }
    }

    /**
     * Parses the command given to Duke and creates a UpdateCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return UpdateCommand object to be created.
     * @throws DukeException If input is not in the format update [task number] [new description].
     */
    public static UpdateCommand process(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("OOPS!!! Please enter in the format update [task number] [new "
                    + "description]");
        }
        String[] currArray = fullCommand[1].split("\\s+", 2);
        if (currArray.length == 1) {
            throw new DukeException("OOPS!!! Please enter in the format update [task number] [new "
                    + "description]");
        }
        return new UpdateCommand(currArray);
    }

    /**
     * Executes the UpdateCommand.
     *
     * @param tasks Task list to refer to.
     * @param storage Storage where the list is updated with the new description.
     * @return Response to user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(tasks.updateDescription(taskNumber, newDescription));
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Your specified task number is out of range.";
        }
        try {
            storage.store(tasks);
        } catch (IOException e) {
            sb.append("OOPS!!! " + e.getMessage());
        }
        return sb.toString();
    }
}
