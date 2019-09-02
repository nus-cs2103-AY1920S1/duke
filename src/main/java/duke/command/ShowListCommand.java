package duke.command;

/**
 * A Command which is used to tell Duke display the contents of the TaskList.
 */
public class ShowListCommand extends Command {

    /**
     * Constructs the Command which displays the Tasks in the TaskList
     */
    ShowListCommand() {
        super(Type.COMMAND_SHOW_LIST);
    }
}