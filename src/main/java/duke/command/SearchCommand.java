package duke.command;

/**
 * A Command which is used to instruct Duke to search for a Task which contains a given keyword in the TaskList.
 */
public class SearchCommand extends Command {

    /**
     * Constructs the Command which is used to instruct Duke to search for a Task which
     * contains a given keyword in the TaskList.
     *
     * @param keyword The keyword the user wants to find a matching Task for in the TaskList
     */
    SearchCommand(String keyword) {
        super(Type.COMMAND_SEARCH, keyword);
    }
}