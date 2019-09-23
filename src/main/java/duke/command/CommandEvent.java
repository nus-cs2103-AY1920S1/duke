package duke.command;

import static duke.command.Messages.MSG_MISSING_EVENT_SPAN;
import static duke.command.Messages.MSG_MISSING_TASK;

import duke.date.DateUtil;
import duke.exception.DukeException;
import duke.command.exception.MissingDescriptionException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "event".
 */
public class CommandEvent extends Command {

    public static final String COMMAND_WORD = "event";

    private static final String REGEX = "(/from)|(to)";

    public CommandEvent(String cmd) {
        super(cmd);
        super.type = "Event: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        String[] commands = command.split(REGEX);
        if (this.command.isBlank() || command.indexOf("/") == 0) {
            throw new MissingDescriptionException(MSG_MISSING_TASK);
        } else if (commands.length < 3) {
            throw new MissingDescriptionException(MSG_MISSING_EVENT_SPAN);
        } else {
            String description = commands[0].trim();
            String start = commands[1].trim();
            String end = commands[2].trim();
            Task evTask = new Event(description, DateUtil.toTime(start), DateUtil.toTime(end));
            sh.add(evTask);
            ui.showAdd(evTask.toString().trim(), sh.getNumOfTask());
            stor.save(sh.getList());
        }
    }

}
