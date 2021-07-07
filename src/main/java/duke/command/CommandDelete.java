package duke.command;

import static duke.command.Messages.MSG_EMPTY_LIST;
import static duke.command.Messages.MSG_ILLEGAL_ENTRY;
import static duke.command.Messages.MSG_MISSING_INDEX;
import static duke.command.Messages.MSG_NON_POSITIVE_INDEX;
import static duke.command.Messages.MSG_TASK_INDEX_EXCEEDED;

import duke.command.exception.MissingDescriptionException;
import duke.exception.DukeException;
import duke.command.exception.IllegalTaskIndexException;
import duke.command.exception.TaskNotFoundException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "delete".
 */
public class CommandDelete extends Command {

    public static final String COMMAND_WORD = "delete";

    public CommandDelete(String command) {
        super(command);
        super.type = "Delete: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        try {
            if (command.isBlank()) {
                throw new MissingDescriptionException(MSG_MISSING_INDEX);
            }
            int index = Integer.parseInt(command);
            if (index > sh.getNumOfTask()) {
                if (sh.isEmpty()) {
                    throw new TaskNotFoundException(MSG_EMPTY_LIST);
                } else {
                    throw new TaskNotFoundException(String.format(MSG_TASK_INDEX_EXCEEDED, sh.getNumOfTask()));
                }
            }
            if (index < 1) {
                throw new TaskNotFoundException(MSG_NON_POSITIVE_INDEX);
            }
            Task removed = sh.delete(index);
            ui.showRemove(removed.toString().trim(), sh.getNumOfTask());
            stor.save(sh.getList());
        } catch (NumberFormatException e) {
            throw new IllegalTaskIndexException(MSG_ILLEGAL_ENTRY);
        }
    }

}
