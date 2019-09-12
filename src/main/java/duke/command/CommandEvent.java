package duke.command;

import duke.date.DateUtil;
import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "event".
 */
public class CommandEvent extends Command {

    public CommandEvent(String cmd) {
        super(cmd);
        super.type = "Event: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        String[] commands = command.split("(/from)|(to)");
        if (this.command.isBlank() || command.indexOf("/") == 0) {
            throw new MissingDescriptionException(
                    "> < Oh! Did you forget to add the task?");
        } else if (commands.length < 3) {
            throw new MissingDescriptionException(
                    "> < OOPS!!! The event span is incomplete.");
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

    @Override
    public String toString() {
        return "Event: " + command;
    }
}
