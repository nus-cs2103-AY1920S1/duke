package duke.commands;
import duke.core.TaskList;
import duke.core.Ui;
import duke.errors.DukeAssertions;

/**
 * Represents a command which contains an execute method that shows a help page.
 */
public class HelpCommand extends Command {

    /**
     * Initialises the list command
     */
    public HelpCommand(){
        super(CommandType.COMMAND_HELP);
    }

    /**
     * Shows a help page to the user
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        DukeAssertions.assertNotNull(taskList, ui);
        return ui.printHelpMessage();
    }
}