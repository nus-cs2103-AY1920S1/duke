import java.util.ArrayList;
import java.util.HashMap;

public class SearchCommand extends Command {
    private String searchDescription;

    public SearchCommand(String searchDescription) {
        super();
        isExit = false;
        this.searchDescription = searchDescription;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        HashMap<Integer, Task> searchResults = tasks.search(searchDescription);
        ui.showSearchResults(searchResults);
    }
}
