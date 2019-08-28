package duke;

public class FindCommand extends Command {
    FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        String searchString;
        try {
            searchString = fullCommand.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Find command needs an argument");
        }
        TaskList filteredTasks = tasks.search(searchString);
        ui.showFindResult(filteredTasks);
    }
}
