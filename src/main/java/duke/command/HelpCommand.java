package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {

    private static final String HELP_MSG = "To try my amazing functions, you can type:\n" +
            "\uD83D\uDC9A todo [description]: Add a simple todo task!\n" +
            "\uD83E\uDDE1 deadline [description] /by [date/month/year hh:mm]: Add a task with omg deadline!\n" +
            "\uD83D\uDC9B event [description] /at [date/month/year hh:mm-hh:mm]: Add a task with specific duration!\n" +
            "\uD83D\uDC9A done [task number]: Mark a task as done!\n" +
            "\uD83D\uDC99 delete [task number]: Delete a useless task!\n" +
            "\uD83D\uDC9C list: See all your wonderful tasks!\n" +
            "\uD83D\uDC9A find [keyword]: Find all tasks containing the keyword!\n" +
            "\uD83E\uDDE1 bye: Then goodbye!\n" +
            "\u2764\ufe0f\u2764\ufe0f\u2764\ufe0f Try to follow the format closely as I am not as smart as Siri =(";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return HELP_MSG;
    }
}
