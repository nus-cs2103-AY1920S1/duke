package duke.command;

/**
 * A Command which is used to tell Duke to search for a Task containing some given keyword in the TaskList.
 */
public class SearchCommand extends Command {
    SearchCommand(String keyword) {
        super(Type.COMMAND_SEARCH, keyword);
    }
}