package duke.command;

import static duke.command.Messages.MSG_MISSING_DEADLINE;
import static duke.command.Messages.MSG_MISSING_TASK;

import duke.date.DateUtil;
import duke.exception.DukeException;
import duke.command.exception.MissingDescriptionException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "deadline".
 */
public class CommandDeadline extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String REGEX = "/by";

    public CommandDeadline(String command) {
        super(command);
        super.type = "Deadline: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        String[] commands = command.split(REGEX);
        if (this.command.isBlank() || command.indexOf("/") == 0) {
            throw new MissingDescriptionException(MSG_MISSING_TASK);
        } else if (commands.length == 1) {
            throw new MissingDescriptionException(MSG_MISSING_DEADLINE);
        } else {
            String description = commands[0].trim();
            String deadline = commands[1].trim();
            Task dlTask = new Deadline(description, DateUtil.toTime(deadline));
            sh.add(dlTask);
            ui.showAdd(dlTask.toString().trim(), sh.getNumOfTask());
            stor.save(sh.getList());
        }
    }

}
