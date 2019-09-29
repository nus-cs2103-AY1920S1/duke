package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a <code>Command</code> to help the user know all available commands.
 */
public class HelpCommand extends Command {

    private static final String HELP_MSG = "To try my amazing functions, you can type:\n"
            + "💗 todo [description]: Add a simple todo task!\n"
            + "💚 deadline [description] /by [date/month/year hh:mm]: Add a task with omg deadline!\n"
            + "💛 event [description] /at [date/month/year hh:mm-hh:mm]: Add a task with specific duration!\n"
            + "💙 done [task number]: Mark a task as done!\n"
            + "💗 delete [task number]: Delete a useless task!\n"
            + "💚 list: See all your wonderful tasks!\n"
            + "💛 find [keyword]: Find all tasks containing the keyword!\n"
            + "💙 bye: Then goodbye!\n"
            + "♥️♥️♥️ Try to follow the format closely as I am not as smart as Siri =(";

    /**
     * Returns a brief introduction to all commands.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return a string showing the usage of commands
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return HELP_MSG;
    }
}
