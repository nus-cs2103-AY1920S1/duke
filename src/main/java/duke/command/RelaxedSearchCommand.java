package duke.command;

/**
 * A Command which is used to instruct Duke to search for a Task which contains a non case-sensitive
 * fuzzy-match to the given keyword in the TaskList.
 */
public class RelaxedSearchCommand extends Command {

    /**
     * Constructs the Command which is used to instruct Duke to relaxed search for a Task which
     * contains a given keyword in the TaskList.
     *
     * @param keyword The keyword the user wants to find a matching Task for in the TaskList
     */
    RelaxedSearchCommand(String keyword) {
        super(duke.command.Type.COMMAND_RELAX_SEARCH, keyword);
        assert keyword != null;
    }
}