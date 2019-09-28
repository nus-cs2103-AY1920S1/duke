package commands;

import logic.DukeException;
import logic.DukeList;
import logic.DukeStrings;
import logic.Parser;
import logic.Storage;
import logic.Ui;
import task.Deadlines;
import task.Task;

import java.time.LocalDateTime;

/**
 * Encapsulates command to add a Deadline Obj to TaskList.
 */
public class DeadlineCommand extends TaskCommands {
    private String args;

    public DeadlineCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the DeadlineCommand.
     *
     * @param list   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If command arguments is invalid
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = args.split("/by");

        if (splitStr.length == 1) {
            throw new DukeException(DukeStrings.DEADLINE_WRONG_FORMAT);
        } else if (splitStr[0].trim().isEmpty()) {
            throw new DukeException(DukeStrings.DEADLINE_EMPTY);
        }

        LocalDateTime deadline = Parser.parseDateTime(splitStr[1].trim());
        Task task = new Deadlines(false, splitStr[0].trim(), deadline);
        list.add(task);
        storage.updateTaskFile(list);
    }


}
