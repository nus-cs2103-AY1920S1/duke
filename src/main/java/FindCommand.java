/**
 * Represents the Command for searching Tasks with a specified keyword.
 * A sub-class of Command.
 */
public class FindCommand extends Command {

    /**
     * Overridden execute method from Command to search for Task objects whose
     * description matches the search term. The method will output all Tasks that
     * contain the search term in the description. It will throw an exception if
     * the search term is empty.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing method and user input field
     * @param input   User input of search term
     * @return Search Task Message
     * @throws DukeException If searchTerm is empty
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Search term cannot be blank.");
        }
        return tasks.searchTaskList(input);
    }
}
