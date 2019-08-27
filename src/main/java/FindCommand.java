import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchString;

    public FindCommand(String command, String searchString) {
        super(command);
        this.searchString = searchString;
    }
    
    public String execute(TaskList tasks, Storage fileMgr) {
        ArrayList<Task> results = tasks.searchTask(this.searchString);
        int numResults = results.size();

        StringBuilder list = new StringBuilder();
        list.append("Here are the matching tasks in your list:");
        
        for (int i = 0; i < numResults; i++) {
            list.append(String.format("\n%d. ", i + 1));
            list.append(results.get(i).toString());
        }
        return list.toString();
    }
}
