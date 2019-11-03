package command;

import main.Archive;
import main.Helper;
import main.Storage;
import main.TaskList;
import main.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A HelpCommand Object to deal with help requests from the user.
 */
public class HelpCommand extends Command {

    private String command = "";
    ArrayList<String> commandList = new ArrayList<>(
            Arrays.asList("bye", "list", "done", "delete", "todo", "event", "deadline", "archive", "find"));

    /**
     * Creates a new HelpCommand.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Creates a new HelpCommand.
     */
    public HelpCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes the command to help the user print out a list of allowable input formats.
     *
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @param archive   The Archive object for archiving purposes
     * @return          The message to be displayed upon successful execution
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {
        Helper helper = new Helper();
        String res;
        if (command.equals("")) {
            res = ui.dukeEchoString(helper.getCommands());
        } else {
            res = ui.dukeEchoString(helper.getSpecificCommand(command));
        }

        return res;
    }
}
