package duke.command;

/**
 * A Command which is used to instruct Duke display the contents of the TaskList.
 */
public class ShowListCommand extends Command {

    /**
     * Constructs the Command which is used to instruct Duke display the contents of the TaskList.
     */
    ShowListCommand() {
        super(Type.COMMAND_SHOW_LIST);
    }
}