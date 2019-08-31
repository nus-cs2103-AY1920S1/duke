package duke.command;

/**
 * Data structure to wrap the command to search for a task in the task list
 */
public class SearchCommand extends Command {
    public SearchCommand(String keyword) {
        super(Type.TYPE_SEARCH, keyword);
    }
}